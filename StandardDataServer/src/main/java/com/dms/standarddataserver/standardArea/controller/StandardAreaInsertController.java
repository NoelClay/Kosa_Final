package com.dms.standarddataserver.standardArea.controller;

import com.dms.standarddataserver.global.LogDefault;
import com.dms.standarddataserver.standardArea.dto.StandardAreaDTO;
import com.dms.standarddataserver.standardArea.service.StandardAreaCheckDuplicateService;
import com.dms.standarddataserver.standardArea.service.StandardAreaInsertService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/std/standardArea")
public class StandardAreaInsertController {
    private final StandardAreaInsertService standardAreaInsertService;
    private final StandardAreaCheckDuplicateService standardAreaCheckDuplicateService;
    private final LogDefault logDefault;

    public StandardAreaInsertController(StandardAreaInsertService standardAreaInsertService, StandardAreaCheckDuplicateService standardAreaCheckDuplicateService, LogDefault logDefault) {
        this.standardAreaInsertService = standardAreaInsertService;
        this.standardAreaCheckDuplicateService = standardAreaCheckDuplicateService;
        this.logDefault = logDefault;
    }

    @PostMapping(value = "/insert")
    public ResponseEntity<Boolean> insert(@RequestBody StandardAreaDTO standardAreaDTO) {
        logDefault.logCurrentMethod();
        standardAreaInsertService.insert(standardAreaDTO);
        boolean insertResult = standardAreaCheckDuplicateService.checkIfDuplicate(standardAreaDTO.getStdAreaNm());
        return ResponseEntity.status(HttpStatus.OK).body(insertResult);
    }
}