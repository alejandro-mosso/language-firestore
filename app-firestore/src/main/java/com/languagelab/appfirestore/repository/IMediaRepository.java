package com.languagelab.appfirestore.repository;

import com.languagelab.appfirestore.model.IMediaModel;
import com.languagelab.appfirestore.repository.exception.RepositoryException;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface IMediaRepository {
    IMediaModel deleteById(String title, String id) throws ExecutionException, InterruptedException, RepositoryException;
    IMediaModel delete(String title, IMediaModel item) throws RepositoryException;
    IMediaModel save(String title, IMediaModel item) throws RepositoryException;
    List<IMediaModel> findAll(String document) throws ExecutionException, InterruptedException;
    List<IMediaModel> findByTitle(String document, String title) throws ExecutionException, InterruptedException;
    List<IMediaModel> findByKeyword(String document, String keyword) throws ExecutionException, InterruptedException;
    List<IMediaModel> findByHistoryId(String idHistory) throws ExecutionException, InterruptedException;
}
