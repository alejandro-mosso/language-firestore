package com.languagelab.appfirestore.repository;

import com.languagelab.appfirestore.model.IMediaModel;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.concurrent.ExecutionException;

@SpringBootTest
public class IMediaRepositoryTest {

    private static Logger logger = LoggerFactory.getLogger(HistoryItemRepositoryTest.class);

    @Autowired
    private IMediaRepository repo;

    @Test
    public void findAllTest() throws ExecutionException, InterruptedException {
        logger.info("****************************************************************");
        List<IMediaModel> list = repo.findAll("Howards End");
        for (IMediaModel item : list) {
            logger.info(item.toString());
        }
    }

    @Test
    public void findByHistoryId() throws ExecutionException, InterruptedException {
        logger.info("****************************************************************");
        List<IMediaModel> list = repo.findByHistoryId("Howards End");
        for (IMediaModel item : list) {
            logger.info(item.toString());
        }
    }
}
