package com.dms.standarddataserver.word.controller;


import com.dms.standarddataserver.word.dto.WordDTO;
import com.dms.standarddataserver.word.service.WordUpdateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/std")
@Slf4j
public class WordUpdateController {

    private final WordUpdateService wordUpdateService;

    public WordUpdateController(WordUpdateService wordUpdateService) {
        this.wordUpdateService = wordUpdateService;
    }

    @PostMapping(value = "/single-word/update")
    public ResponseEntity<?> wordUpdate(@RequestBody WordDTO wordDTO) {

        boolean isWordUpdated = wordUpdateService.updateWord(wordDTO);


        return ResponseEntity.status(HttpStatus.OK).body(isWordUpdated);


    }
}
