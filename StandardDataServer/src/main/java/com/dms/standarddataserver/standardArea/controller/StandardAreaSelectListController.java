package com.dms.standarddataserver.standardArea.controller;

import com.dms.standarddataserver.global.LogDefault;
import com.dms.standarddataserver.standardArea.dto.StandardAreaDTO;
import com.dms.standarddataserver.standardArea.service.StandardAreaSelectListService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/std/standardArea")
public class StandardAreaSelectListController {
    private final StandardAreaSelectListService standardAreaSelectListService;
    private final LogDefault logDefault;

    public StandardAreaSelectListController(StandardAreaSelectListService standardAreaSelectListService, LogDefault logDefault) {
        this.standardAreaSelectListService = standardAreaSelectListService;
        this.logDefault = logDefault;
    }

    @PostMapping(value = "/selectList")
    public ResponseEntity<List<StandardAreaDTO>> selectList(@RequestBody String userId) {
        logDefault.logCurrentMethod();
        return ResponseEntity.status(HttpStatus.OK).body(standardAreaSelectListService.selectList(userId));
    }
}
