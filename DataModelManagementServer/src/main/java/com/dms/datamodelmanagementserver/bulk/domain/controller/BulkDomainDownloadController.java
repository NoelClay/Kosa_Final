package com.dms.datamodelmanagementserver.bulk.domain.controller;

import com.dms.datamodelmanagementserver.bulk.domain.dto.DomainExcelDataDTO;
import com.dms.datamodelmanagementserver.bulk.domain.service.BulkDomainDownloadService;
import com.dms.datamodelmanagementserver.global.LogDefault;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/dms")
public class BulkDomainDownloadController {

    private final BulkDomainDownloadService bulkDomainDownloadService;
    private final LogDefault logDefault;

    @Autowired
    public BulkDomainDownloadController(BulkDomainDownloadService bulkDomainDownloadService, LogDefault logDefault) {
        this.bulkDomainDownloadService = bulkDomainDownloadService;
        this.logDefault = logDefault;
    }

    @PostMapping("/bulk-domain/download")
    public ResponseEntity<String> downloadBulkDomain(@RequestBody List<DomainExcelDataDTO> domainExcelDataDTOList, HttpServletResponse response) throws IOException {
        logDefault.logCurrentMethod();
        return ResponseEntity.ok(bulkDomainDownloadService.saveDomainDataToExcel(domainExcelDataDTOList, response));
    }
}
