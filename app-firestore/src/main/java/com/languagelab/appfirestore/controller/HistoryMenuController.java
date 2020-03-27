package com.languagelab.appfirestore.controller;

import com.languagelab.appfirestore.model.HistoryItemModel;
import com.languagelab.appfirestore.repository.exception.RepositoryException;
import com.languagelab.appfirestore.service.HistoryMenuService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 *
 * Check OpenAPI FAQ https://springdoc.github.io/springdoc-openapi-demos/faq.html
 *
 */

@Slf4j
@RestController
@RequestMapping("/languagelab/history-menu")
public class HistoryMenuController {
    @Autowired
    private HistoryMenuService service;

    private static Logger logger = LoggerFactory.getLogger(HistoryMenuController.class);

    @GetMapping(value = "/", produces = "application/json")
    @ResponseBody
    public ResponseEntity<List<HistoryItemModel>> get(@RequestParam(required = false) String title) throws ExecutionException, InterruptedException {
        if (title != null) {
            List<HistoryItemModel> result = service.findByTitle(title);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        else {
            List<HistoryItemModel> result = service.findAllHistories();
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
    }

    @GetMapping(value = "/language", produces = "application/json")
    @ResponseBody
    @CrossOrigin(origins = "*")
    public ResponseEntity<List<HistoryItemModel>> getByLanguage(
            @Parameter(description = "Indicates the language you want to learn (in lower case).",
                    example = "english")
            @RequestParam(required = true) String src,
            @Parameter(description = "Indicates the language you already speak (in lower case).",
                    example = "español")
            @RequestParam(required = true) String tar) throws ExecutionException, InterruptedException {
        List<HistoryItemModel> result = service.findByLanguage(src, tar);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(produces = "application/json", consumes = "application/json")
    @ResponseBody
    @Hidden
    public ResponseEntity<HistoryItemModel> post(@RequestBody HistoryItemModel model) throws RepositoryException {
        logger.debug("We will add new title: " + model.getTitle());
        return new ResponseEntity<>(this.service.save(model), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseBody
    @Hidden
    public ResponseEntity<HistoryItemModel> delete(@PathVariable String id) throws InterruptedException, ExecutionException, RepositoryException {
        return new ResponseEntity<>(this.service.delete(id), HttpStatus.OK);
    }
}
