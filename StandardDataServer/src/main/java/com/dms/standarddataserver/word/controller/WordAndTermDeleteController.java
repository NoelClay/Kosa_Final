package com.dms.standarddataserver.word.controller;


import com.dms.standarddataserver.word.service.WordAndTermDeleteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/std")
@Slf4j
public class WordAndTermDeleteController {

    private final WordAndTermDeleteService wordAndTermDeleteService;
    
    @Autowired
    public WordAndTermDeleteController(WordAndTermDeleteService wordAndTermDeleteService) {
        this.wordAndTermDeleteService = wordAndTermDeleteService;
    }


    @PostMapping("/single-wordAndTerm/delete")
    public ResponseEntity<Boolean> deleteWordAndTerm(@RequestBody Map<String, String> requestMap){

        String deleteDicId = requestMap.get("dicId");

        boolean isWordAndTermDeleted = wordAndTermDeleteService.deleteWordAndTerm(deleteDicId);

        return ResponseEntity.status(HttpStatus.OK).body(isWordAndTermDeleted);


    }

}



