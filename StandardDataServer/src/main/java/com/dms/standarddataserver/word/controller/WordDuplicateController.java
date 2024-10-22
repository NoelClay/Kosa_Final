package com.dms.standarddataserver.word.controller;

import com.dms.standarddataserver.word.dto.WordDTO;
import com.dms.standarddataserver.word.service.WordDuplicateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/std")
@Slf4j
public class WordDuplicateController {

    private final WordDuplicateService wordDuplicateService;

    @Autowired
    public WordDuplicateController(WordDuplicateService wordDuplicateService) {
        this.wordDuplicateService = wordDuplicateService;
    }


    @PostMapping("/single-word/duplicate")
    public ResponseEntity<Boolean> wordDuplicate(@RequestBody WordDTO wordDTO) {
        log.info("::STD DUPLICATE CONTROLLER::START");

        log.info("::STD DUPLICATE CONTROLLER" +  wordDTO);

        boolean isDuplicate = wordDuplicateService.isDuplicate(wordDTO);

        log.info("::STD DUPLICATE CONTROLLER::END=" + isDuplicate);

        return ResponseEntity.status(HttpStatus.OK).body(isDuplicate);
    }




}
