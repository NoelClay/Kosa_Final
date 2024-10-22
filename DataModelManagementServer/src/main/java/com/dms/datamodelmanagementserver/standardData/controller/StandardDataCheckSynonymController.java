package com.dms.datamodelmanagementserver.standardData.controller;

import com.dms.datamodelmanagementserver.global.LogDefault;
import com.dms.datamodelmanagementserver.standardData.dto.StandardDataDTO;
import com.dms.datamodelmanagementserver.standardData.service.StandardDataCheckSynonymService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/dms/standardData")
@Slf4j
public class StandardDataCheckSynonymController {
    private final LogDefault logDefault;
    private final StandardDataCheckSynonymService standardDataCheckSynonymService;

    public StandardDataCheckSynonymController(LogDefault logDefault, StandardDataCheckSynonymService standardDataCheckSynonymService) {
        this.logDefault = logDefault;
        this.standardDataCheckSynonymService = standardDataCheckSynonymService;
    }

    @PostMapping(value = "/checkSynonym")
    public ResponseEntity<StandardDataDTO> checkSynonym(@RequestBody StandardDataDTO standardDataDTO) {
        logDefault.logCurrentMethod();
        log.info(standardDataDTO.toString());
        return ResponseEntity.status(HttpStatus.OK).body(standardDataCheckSynonymService.checkSynonym(standardDataDTO));
    }
}
