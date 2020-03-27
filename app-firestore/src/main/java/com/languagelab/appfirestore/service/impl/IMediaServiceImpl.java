package com.languagelab.appfirestore.service.impl;

import com.languagelab.appfirestore.model.IMediaModel;
import com.languagelab.appfirestore.repository.IMediaRepository;
import com.languagelab.appfirestore.repository.exception.RepositoryException;
import com.languagelab.appfirestore.service.IMediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class IMediaServiceImpl implements IMediaService {

    @Autowired
    IMediaRepository dao;

    @Override
    public IMediaModel save(IMediaModel model) throws RepositoryException {
        return this.dao.save(model.getTitle(), model);
    }

    @Override
    public IMediaModel delete(String title, String id) throws InterruptedException, ExecutionException, RepositoryException {
        return this.dao.deleteById(title, id);
    }

    @Override
    public List<IMediaModel> findByKeyWord(String document, String keyWord) throws ExecutionException, InterruptedException {
        return this.dao.findByKeyword(document, keyWord);
    }

    @Override
    public List<IMediaModel> findByHistoryId(String document) throws ExecutionException, InterruptedException {
        return this.dao.findByHistoryId(document);
    }
}
