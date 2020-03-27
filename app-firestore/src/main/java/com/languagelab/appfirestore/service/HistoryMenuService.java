package com.languagelab.appfirestore.service;

import com.languagelab.appfirestore.model.HistoryItemModel;
import com.languagelab.appfirestore.repository.exception.RepositoryException;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface HistoryMenuService {
    List<HistoryItemModel> findAllHistories() throws ExecutionException, InterruptedException;
    List<HistoryItemModel> findByTitle(String title) throws ExecutionException, InterruptedException;
    List<HistoryItemModel> findByLanguage(String src, String tar) throws ExecutionException, InterruptedException;
    HistoryItemModel save(HistoryItemModel model) throws RepositoryException;
    HistoryItemModel delete(String id) throws InterruptedException, ExecutionException, RepositoryException;
}
