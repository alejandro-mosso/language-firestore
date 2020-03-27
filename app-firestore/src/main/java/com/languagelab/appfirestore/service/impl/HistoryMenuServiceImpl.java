package com.languagelab.appfirestore.service.impl;

import com.languagelab.appfirestore.model.HistoryItemModel;
import com.languagelab.appfirestore.repository.HistoryItemRepository;
import com.languagelab.appfirestore.repository.exception.RepositoryException;
import com.languagelab.appfirestore.service.HistoryMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class HistoryMenuServiceImpl implements HistoryMenuService {

    @Autowired
    HistoryItemRepository dao;

    @Override
    public List<HistoryItemModel> findAllHistories() throws ExecutionException, InterruptedException {
        return this.dao.findAll();
    }

    @Override
    public List<HistoryItemModel> findByTitle(String title) throws ExecutionException, InterruptedException {
        return this.dao.findByTitle(title);
    }

    @Override
    public List<HistoryItemModel> findByLanguage(String src, String tar) throws ExecutionException, InterruptedException {
        return this.dao.findByLanguage(src, tar);
    }

    @Override
    public HistoryItemModel save(HistoryItemModel model) throws RepositoryException {
        return this.dao.save(model);
    }

    @Override
    public HistoryItemModel delete(String id) throws InterruptedException, ExecutionException, RepositoryException {
        return this.dao.deleteById(id);
    }
}
