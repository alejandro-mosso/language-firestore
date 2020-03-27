package com.languagelab.appfirestore.controller;

import com.languagelab.appfirestore.model.IMediaModel;
import com.languagelab.appfirestore.repository.exception.RepositoryException;
import com.languagelab.appfirestore.service.IMediaService;
import io.swagger.v3.oas.annotations.Hidden;
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
@RequestMapping("/languagelab/i-media")
public class IMediaController {

    @Autowired
    private IMediaService service;

    private static Logger logger = LoggerFactory.getLogger(HistoryMenuController.class);

    @GetMapping(value = "/", produces = "application/json")
    @ResponseBody
    public ResponseEntity<List<IMediaModel>> get(@RequestParam(required = true) String document, @RequestParam(required = true) String keyWord) throws ExecutionException, InterruptedException {
        List<IMediaModel> result = service.findByKeyWord(document, keyWord);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/history", produces = "application/json")
    @ResponseBody
    @CrossOrigin(origins = "*")
    public ResponseEntity<List<IMediaModel>> getByHistoryId(@RequestParam(required = true) String id) throws ExecutionException, InterruptedException {
        List<IMediaModel> result = service.findByHistoryId(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(produces = "application/json", consumes = "application/json")
    @ResponseBody
    @Hidden
    public ResponseEntity<IMediaModel> post(@RequestBody IMediaModel model) throws RepositoryException {
        logger.debug("We will add new title: " + model.getTitle());
        return new ResponseEntity<>(this.service.save(model), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{document}/{id}")
    @ResponseBody
    @Hidden
    public ResponseEntity<IMediaModel> delete(@PathVariable String document, @PathVariable String id) throws InterruptedException, ExecutionException, RepositoryException {
        return new ResponseEntity<>(this.service.delete(document, id), HttpStatus.OK);
    }
}
