package com.dms.standarddataserver.standardArea.controller;

import com.dms.standarddataserver.global.LogDefault;
import com.dms.standarddataserver.standardArea.dto.StandardAreaDTO;
import com.dms.standarddataserver.standardArea.service.StandardAreaCheckDuplicateService;
import com.dms.standarddataserver.standardArea.service.StandardAreaUpdateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "/std/standardArea")
public class StandardAreaUpdateController {
    private final LogDefault logDefault;
    private final StandardAreaCheckDuplicateService standardAreaCheckDuplicateService;
    private final StandardAreaUpdateService standardAreaUpdateService;

    public StandardAreaUpdateController(LogDefault logDefault, StandardAreaCheckDuplicateService standardAreaCheckDuplicateService, StandardAreaUpdateService standardAreaUpdateService) {
        this.logDefault = logDefault;
        this.standardAreaCheckDuplicateService = standardAreaCheckDuplicateService;
        this.standardAreaUpdateService = standardAreaUpdateService;
    }

    @PostMapping(value = "/update")
    public ResponseEntity<Boolean> update(@RequestBody StandardAreaDTO standardAreaDTO) {
        logDefault.logCurrentMethod();
        boolean isDuplicate = standardAreaCheckDuplicateService.checkIfDuplicate(standardAreaDTO.getMessage());
        if (!isDuplicate) {
            return ResponseEntity.status(HttpStatus.OK).body(false);
        }
        standardAreaUpdateService.update(standardAreaDTO);
        boolean result = standardAreaCheckDuplicateService.checkIfDuplicate(standardAreaDTO.getStdAreaNm());
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
