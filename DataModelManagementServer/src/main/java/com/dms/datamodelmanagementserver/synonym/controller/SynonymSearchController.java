package com.dms.datamodelmanagementserver.synonym.controller;

import com.dms.datamodelmanagementserver.global.LogDefault;
import com.dms.datamodelmanagementserver.synonym.dto.SynonymDTO;
import com.dms.datamodelmanagementserver.synonym.service.SynonymSearchService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/dms")
public class SynonymSearchController {

    private final SynonymSearchService synonymSearchService;
    private final LogDefault logDefault;

    public SynonymSearchController(SynonymSearchService synonymSearchService, LogDefault logDefault) {
        this.synonymSearchService = synonymSearchService;
        this.logDefault = logDefault;
    }

    @ResponseBody
    @PostMapping(value = "/synonym/searchList")
    public ResponseEntity<List<SynonymDTO>> selectList(@RequestBody SynonymDTO synonymDTO) {
    	logDefault.logCurrentMethod();
        return ResponseEntity.status(HttpStatus.OK).body(synonymSearchService.selectList(synonymDTO));
    }
}


