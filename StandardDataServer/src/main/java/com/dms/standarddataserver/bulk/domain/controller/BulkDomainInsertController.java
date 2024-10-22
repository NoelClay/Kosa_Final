package com.dms.standarddataserver.bulk.domain.controller;

import com.dms.standarddataserver.bulk.domain.dto.DomainExcelDataDTO;
import com.dms.standarddataserver.bulk.domain.service.BulkDomainInsertService;
import com.dms.standarddataserver.global.LogDefault;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/std")
public class BulkDomainInsertController {

    private final BulkDomainInsertService bulkDomainInsertService;
    private final LogDefault logDefault;

    @Autowired
    public BulkDomainInsertController(BulkDomainInsertService bulkDomainInsertService, LogDefault logDefault) {
        this.bulkDomainInsertService = bulkDomainInsertService;
        this.logDefault = logDefault;
    }

    @PostMapping("/bulk-domain/insert")
    private ResponseEntity<?> insertBulkDomain(@RequestBody List<DomainExcelDataDTO> domainExcelDataDTOList) {
        logDefault.logCurrentMethod();
        List<DomainExcelDataDTO> result = bulkDomainInsertService.insertBulkDomain(domainExcelDataDTOList);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
