package com.dms.standarddataserver.term.controller;

import com.dms.standarddataserver.term.service.TermSearchService;
import com.dms.standarddataserver.word.dto.WordDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/std")
@Slf4j
public class TermSearchController {

    private final TermSearchService termSearchService;

    @Autowired
    public TermSearchController(TermSearchService termListService) {
        this.termSearchService = termListService;
    }

    @PostMapping("/single-term/search")
    public ResponseEntity<?> getSingleTermList(@RequestBody Map<String, String> requestMap) {
        String stdAreaId = requestMap.get("stdAreaId");
        String searchWord = requestMap.get("searchWord");

        log.info("STD::TermSearchController stdAreaId= " + stdAreaId);
        log.info("STD::TermSearchController searchWord= " + searchWord);
        List<WordDTO> wordList = termSearchService.selectWordList(stdAreaId, searchWord);

        log.info("STD::TermSearchController END = " + wordList);

        return ResponseEntity.status(HttpStatus.OK).body(wordList);
    }





}
