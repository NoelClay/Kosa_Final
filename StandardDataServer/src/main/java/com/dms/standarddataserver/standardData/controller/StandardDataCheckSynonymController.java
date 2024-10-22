package com.dms.standarddataserver.standardData.controller;

import com.dms.standarddataserver.global.LogDefault;
import com.dms.standarddataserver.standardData.dto.StandardDataDTO;
import com.dms.standarddataserver.standardData.service.StandardDataCheckSynonymService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/std/standardData")
@Slf4j
public class StandardDataCheckSynonymController {
    private final StandardDataCheckSynonymService standardDataCheckSynonymService;
    private final LogDefault logDefault;

    public StandardDataCheckSynonymController(StandardDataCheckSynonymService standardDataCheckSynonymService, LogDefault logDefault) {
        this.standardDataCheckSynonymService = standardDataCheckSynonymService;
        this.logDefault = logDefault;
    }

    @PostMapping(value = "/checkSynonym")
    public ResponseEntity<StandardDataDTO> checkSynonym(@RequestBody StandardDataDTO standardDataDTO) {
        logDefault.logCurrentMethod();
        return ResponseEntity.status(HttpStatus.OK).body(standardDataCheckSynonymService.checkSynonym(standardDataDTO));
    }
}
