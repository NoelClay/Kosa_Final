package com.dms.standarddataserver.word.controller;

import com.dms.standarddataserver.word.dto.WordDTO;
import com.dms.standarddataserver.word.service.WordInfoService;
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
public class WordInfoController {

    private final WordInfoService wordInfoService;

    @Autowired
    public WordInfoController(WordInfoService wordInfoService) {
        this.wordInfoService = wordInfoService;
    }




    @PostMapping("/single-wordAndTerm/select")
    public ResponseEntity<?> getWordAndTermInfo(@RequestBody Map<String, String> requestMap) {
        String dicId = requestMap.get("dicId");
        
        WordDTO wordDTO = wordInfoService.getWordAndTermInfo(dicId);

        return ResponseEntity.status(HttpStatus.OK).body(wordDTO);



    }

}
