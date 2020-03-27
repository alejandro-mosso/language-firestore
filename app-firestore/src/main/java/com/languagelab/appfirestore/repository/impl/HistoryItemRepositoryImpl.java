package com.languagelab.appfirestore.repository.impl;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.languagelab.appfirestore.model.HistoryItemModel;
import com.languagelab.appfirestore.repository.HistoryItemRepository;
import com.languagelab.appfirestore.repository.exception.RepositoryException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class HistoryItemRepositoryImpl implements HistoryItemRepository {

    private static Logger logger = LoggerFactory.getLogger(HistoryItemRepositoryImpl.class);

    @Autowired
    Firestore db;

    @Override
    public HistoryItemModel deleteById(String id) throws ExecutionException, InterruptedException, RepositoryException {
        HistoryItemModel item = null;
        List<HistoryItemModel> itemList = this.findByTitle(id);
        if (itemList.size() > 0) {
            item = itemList.get(0);
            this.delete(item);
        }
        return item;
    }

    @Override
    public HistoryItemModel delete(HistoryItemModel item) throws RepositoryException {
        ApiFuture<WriteResult> writeResult = db.collection("LanguageLab").document(item.getTitle()).delete();
        if (writeResult.isCancelled()) {
            throw new RepositoryException("Delete action canceled: " + item.toString());
        }
        return item;
    }

    @Override
    public HistoryItemModel save(HistoryItemModel item) throws RepositoryException {
        ApiFuture<WriteResult> writeResult = db.collection("LanguageLab").document(item.getTitle()).set(item);
        if (writeResult.isCancelled()) {
            throw new RepositoryException("Save action canceled: " + item.toString());
        }
        return item;
    }

    @Override
    public List<HistoryItemModel> findByTitle(String title) throws ExecutionException, InterruptedException {
        List<HistoryItemModel> list = new LinkedList<>();
        CollectionReference collection = db.collection("LanguageLab");
        Query q = collection.whereEqualTo("title", title);

        logger.info("************************* findByTitle");
        for (DocumentSnapshot d : q.get().get().getDocuments()) {
            HistoryItemModel item = d.toObject(HistoryItemModel.class);
            item.setId(item.getTitle());
            list.add(item);
        }

        return list;
    }

    @Override
    public List<HistoryItemModel> findAll() throws ExecutionException, InterruptedException {
        List<HistoryItemModel> list = new LinkedList<>();
        ApiFuture<QuerySnapshot> future = db.collection("LanguageLab").orderBy("title").get();

        logger.info("************************* findAll");
        for (DocumentSnapshot d : future.get().getDocuments()) {
            HistoryItemModel item = d.toObject(HistoryItemModel.class);
            item.setId(item.getTitle());
            list.add(item);
        }

        return list;
    }

    /**
     * This method is used to find all Histories available for a particular source and target languages in MongoDB.
     * @param sourceLanguage The language you want to lear.
     * @param targetLanguage  The language you speak.
     * @return List<HistoryMenuModel> Returns a list Histories that match source and target languages.
     */
    @Override
    public List<HistoryItemModel> findByLanguage(String sourceLanguage, String targetLanguage) throws ExecutionException, InterruptedException {
        List<HistoryItemModel> list = new LinkedList<>();
        CollectionReference collection = db.collection("LanguageLab");
        Query query = collection.whereEqualTo("sourceLanguage", sourceLanguage).
                whereEqualTo("targetLanguage", targetLanguage).orderBy("title");
        ApiFuture<QuerySnapshot> future = query.get();

        logger.info("************************* findByLanguage");
        for (DocumentSnapshot d : future.get().getDocuments()) {
            HistoryItemModel item = d.toObject(HistoryItemModel.class);
            item.setId(item.getTitle());
            list.add(item);
        }

        return list;
    }
}
