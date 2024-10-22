package com.dms.datamodelmanagementserver.standardData.controller;

import com.dms.datamodelmanagementserver.global.LogDefault;
import com.dms.datamodelmanagementserver.standardData.dto.StandardDataDTO;
import com.dms.datamodelmanagementserver.standardData.dto.StandardDataSearchDTO;
import com.dms.datamodelmanagementserver.standardData.service.StandardDataSearchService;
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
@RequestMapping(value = "/dms/standardData")
public class StandardDataSearchController {
    private final LogDefault logDefault;
    private final StandardDataSearchService standardDataSearchService;

    public StandardDataSearchController(LogDefault logDefault, StandardDataSearchService standardDataSearchService) {
        this.logDefault = logDefault;
        this.standardDataSearchService = standardDataSearchService;
    }

    @PostMapping(value = "/conditionSearch")
    public ResponseEntity<List<StandardDataDTO>> search(@RequestBody StandardDataSearchDTO standardDataSearchDTO) {
        logDefault.logCurrentMethod();
        List<StandardDataDTO> response = standardDataSearchService.search(standardDataSearchDTO);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
