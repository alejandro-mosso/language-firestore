package com.languagelab.appfirestore.service;

import com.languagelab.appfirestore.model.IMediaModel;
import com.languagelab.appfirestore.repository.exception.RepositoryException;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface IMediaService {
    IMediaModel save(IMediaModel model) throws RepositoryException;
    IMediaModel delete(String document, String id) throws InterruptedException, ExecutionException, RepositoryException;
    List<IMediaModel> findByKeyWord(String document, String keyWord) throws ExecutionException, InterruptedException;
    List<IMediaModel> findByHistoryId(String idHistory) throws ExecutionException, InterruptedException;
}
