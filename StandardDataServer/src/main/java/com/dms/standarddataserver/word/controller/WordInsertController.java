package com.dms.standarddataserver.word.controller;

import com.dms.standarddataserver.word.dto.WordDTO;
import com.dms.standarddataserver.word.service.WordDuplicateService;
import com.dms.standarddataserver.word.service.WordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@RestController
@RequestMapping("/std")
@Slf4j
public class WordInsertController {
    private static final Logger logger = LoggerFactory.getLogger(WordInsertController.class);
    private final WordDuplicateService wordDuplicateService;
    private final WordService wordService;

    @Autowired
    public WordInsertController(WordDuplicateService wordDuplicateService, WordService wordService) {
        this.wordDuplicateService = wordDuplicateService;
        this.wordService = wordService;
    }


    @PostMapping(value = "/single-word/insert")
    public ResponseEntity<Boolean> wordInsert(@RequestBody WordDTO wordDTO) {
        logger.info("Received a POST request to /std/word/insert");
        logger.info("Received WordDTO: " + wordDTO);



        boolean isWordInserted = wordService.insertWord(wordDTO);


        return ResponseEntity.status(HttpStatus.OK).body(isWordInserted);



    }
}


