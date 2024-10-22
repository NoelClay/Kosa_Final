package com.dms.standarddataserver.synonym.controller;


import com.dms.standarddataserver.synonym.dto.SynonymDTO;
import com.dms.standarddataserver.synonym.service.SynonymSearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/std/synonym")
public class SynonymSearchController {

    private final SynonymSearchService synonymSearchService;


    public SynonymSearchController(SynonymSearchService synonymSearchService) {
        this.synonymSearchService = synonymSearchService;
    }


    @PostMapping(value = "/test")
    public void testList() {
        System.out.println(this.getClass().getName() + "::testList()");
    }

    @PostMapping(value = "/searchList")
    public ResponseEntity<List<SynonymDTO>> selectList(@RequestBody SynonymDTO synonymDTO) {

        return ResponseEntity.status(HttpStatus.OK).body(synonymSearchService.selectList(synonymDTO));
    }
}
