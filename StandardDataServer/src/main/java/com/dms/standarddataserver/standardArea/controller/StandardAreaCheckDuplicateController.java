package com.dms.standarddataserver.standardArea.controller;

import com.dms.standarddataserver.global.LogDefault;
import com.dms.standarddataserver.standardArea.service.StandardAreaCheckDuplicateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/std/standardArea")
public class StandardAreaCheckDuplicateController {
    private final StandardAreaCheckDuplicateService standardAreaCheckDuplicateService;
    private  final LogDefault logDefault;

    public StandardAreaCheckDuplicateController(StandardAreaCheckDuplicateService standardAreaCheckDuplicateService, LogDefault logDefault) {
        this.standardAreaCheckDuplicateService = standardAreaCheckDuplicateService;
        this.logDefault = logDefault;
    }

    @PostMapping(value = "/checkDuplicate")
    public ResponseEntity<Boolean> checkIfDuplicate(@RequestBody String standardAreaName) {
        logDefault.logCurrentMethod();
        boolean isDuplicate = standardAreaCheckDuplicateService.checkIfDuplicate(standardAreaName);
        return ResponseEntity.status(HttpStatus.OK).body(isDuplicate);
    }
}
