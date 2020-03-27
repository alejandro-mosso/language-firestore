package com.languagelab.appfirestore.repository;

import com.languagelab.appfirestore.model.HistoryItemModel;
import com.languagelab.appfirestore.repository.exception.RepositoryException;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface HistoryItemRepository {
    HistoryItemModel deleteById(String id) throws ExecutionException, InterruptedException, RepositoryException;
    HistoryItemModel delete(HistoryItemModel item) throws RepositoryException;
    HistoryItemModel save(HistoryItemModel item) throws RepositoryException;
    List<HistoryItemModel> findByTitle(String title) throws ExecutionException, InterruptedException;
    /**
     * This method is used to find all Histories available for a particular source and target languages in MongoDB.
     * @param sourceLanguage The language you want to lear.
     * @param targetLanguage  The language you speak.
     * @return List<HistoryMenuModel> Returns a list Histories that match source and target languages.
     */
    List<HistoryItemModel> findByLanguage(String sourceLanguage, String targetLanguage) throws ExecutionException, InterruptedException;

    List<HistoryItemModel> findAll() throws ExecutionException, InterruptedException;
}
