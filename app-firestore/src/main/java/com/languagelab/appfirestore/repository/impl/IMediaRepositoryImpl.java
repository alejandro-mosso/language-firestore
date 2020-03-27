package com.languagelab.appfirestore.repository.impl;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.languagelab.appfirestore.model.IMediaModel;
import com.languagelab.appfirestore.repository.IMediaRepository;
import com.languagelab.appfirestore.repository.exception.RepositoryException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class IMediaRepositoryImpl implements IMediaRepository {

    private static Logger logger = LoggerFactory.getLogger(HistoryItemRepositoryImpl.class);

    @Autowired
    Firestore db;

    @Override
    public IMediaModel deleteById(String document, String id) throws ExecutionException, InterruptedException, RepositoryException {
        List<IMediaModel> list = this.findByTitle(document, id);
        IMediaModel deleted = null;
        if (list.size() > 0) {
            deleted = this.delete(document, list.get(0));
        }
        return deleted;
    }

    @Override
    public IMediaModel delete(String document, IMediaModel item) throws RepositoryException {
        ApiFuture<WriteResult> writeResult = db.collection("LanguageLab").document(document).
                collection("Lessons").document(item.getTitle()).delete();
        if (writeResult.isCancelled()) {
            throw new RepositoryException("Delete action canceled: " + item.toString());
        }
        return item;
    }

    @Override
    public IMediaModel save(String document, IMediaModel item) throws RepositoryException {
        ApiFuture<WriteResult> writeResult = db.collection("LanguageLab").document(document).
                collection("Lessons").document(item.getTitle()).set(item);
        if (writeResult.isCancelled()) {
            throw new RepositoryException("Delete action canceled: " + item.toString());
        }
        return item;
    }

    @Override
    public List<IMediaModel> findAll(String document) throws ExecutionException, InterruptedException {
        List<IMediaModel> list = new LinkedList<>();
        ApiFuture<QuerySnapshot> future = db.collection("LanguageLab").document(document).
                collection("Lessons").get();

        logger.info("************************* findAll(" + document + ")");
        for (DocumentSnapshot d : future.get().getDocuments()) {
            IMediaModel item = d.toObject(IMediaModel.class);
            item.setId(item.getTitle());
            list.add(item);
        }

        return list;
    }

    @Override
    public List<IMediaModel> findByTitle(String document, String title) throws ExecutionException, InterruptedException {
        List<IMediaModel> list = new LinkedList<>();
        Query future = db.collection("LanguageLab").document(document).
                collection("Lessons").get().get().getQuery().
                whereEqualTo("title", title);

        logger.info("************************* findByTitle(" + document + ", " + title + ")");
        for (DocumentSnapshot d : future.get().get().getDocuments()) {
            IMediaModel item = d.toObject(IMediaModel.class);
            item.setId(item.getTitle());
            list.add(item);
        }

        return list;
    }

    @Override
    public List<IMediaModel> findByKeyword(String document, String keyword) throws ExecutionException, InterruptedException {
        List<IMediaModel> list = new LinkedList<>();
        ApiFuture<QuerySnapshot> future = db.collection("LanguageLab").document(document).
                collection("Lessons").get();

        logger.info("************************* findByKeyword(" + document + ", " + keyword + ")");
        for (DocumentSnapshot d : future.get().getDocuments()) {
            IMediaModel item = d.toObject(IMediaModel.class);
            if (item.getScript().toLowerCase().contains(keyword.toLowerCase())) {
                item.setId(item.getTitle());
                list.add(item);
            }
        }

        return list;
    }

    @Override
    public List<IMediaModel> findByHistoryId(String document) throws ExecutionException, InterruptedException {
        List<IMediaModel> list = new LinkedList<>();
        ApiFuture<QuerySnapshot> future = db.collection("LanguageLab").document(document).
                collection("Lessons").get();

        logger.info("************************* findByHistoryId (" + document + ")");
        for (DocumentSnapshot d : future.get().getDocuments()) {
            IMediaModel item = d.toObject(IMediaModel.class);
            item.setId(item.getTitle());
            list.add(item);
        }

        return list;
    }
}
