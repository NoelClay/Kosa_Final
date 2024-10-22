package com.dms.standarddataserver.bulk.domain.controller;

import com.dms.standarddataserver.bulk.domain.dto.DomainExcelDataDTO;
import com.dms.standarddataserver.bulk.domain.service.BulkDomainDisplayService;
import com.dms.standarddataserver.global.LogDefault;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/std")
public class BulkDomainDisplayController {

    private final BulkDomainDisplayService bulkDomainDisplayService;
    private final LogDefault logDefault;

    @Autowired
    public BulkDomainDisplayController(BulkDomainDisplayService bulkDomainDisplayService, LogDefault logDefault) {
        this.bulkDomainDisplayService = bulkDomainDisplayService;
        this.logDefault = logDefault;
    }

    @PostMapping("/bulk-domain/validate")
    private ResponseEntity<?> validateBulkDomain(@RequestBody List<DomainExcelDataDTO> domainExcelDataDTOList) {
        logDefault.logCurrentMethod();
        List<DomainExcelDataDTO> domainExcelDataDTOS = bulkDomainDisplayService.validateBulkDomain(domainExcelDataDTOList);
        return ResponseEntity.status(HttpStatus.OK).body(domainExcelDataDTOS);
    }
}
