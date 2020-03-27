package com.languagelab.appfirestore.repository;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.ExecutionException;

@SpringBootTest
public class HistoryItemRepositoryTest {

    private static Logger logger = LoggerFactory.getLogger(HistoryItemRepositoryTest.class);

    @Autowired
    HistoryItemRepository repo;

    @Test
    public void findAllTest() throws ExecutionException, InterruptedException {
        logger.info("****************************************************************");
        repo.findAll();
    }


    @Test
    public void findByLanguage() throws ExecutionException, InterruptedException {
        logger.info("****************************************************************");
        /**
         * @param sourceLanguage The language you want to lear.
         * @param targetLanguage  The language you speak.
         */
        String sourceLanguage = "english";
        String targetLanguage = "español";
        repo.findByLanguage(sourceLanguage, targetLanguage);
    }

    @Test
    public void findByTitle() throws ExecutionException, InterruptedException {
        logger.info("****************************************************************");
        String title = "Finding Nemo";
        repo.findByTitle(title);
    }
}
