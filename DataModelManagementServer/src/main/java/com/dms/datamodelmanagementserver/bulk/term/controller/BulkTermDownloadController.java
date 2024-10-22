package com.dms.datamodelmanagementserver.bulk.term.controller;

import com.dms.datamodelmanagementserver.bulk.term.dto.BulkTermExcelDataDTO;
import com.dms.datamodelmanagementserver.bulk.term.service.BulkTermDownloadService;
import com.dms.datamodelmanagementserver.global.LogDefault;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/dms")
public class BulkTermDownloadController {

    private final BulkTermDownloadService bulkTermDownloadService;
    private final LogDefault logDefault;

    public BulkTermDownloadController(BulkTermDownloadService bulkTermDownloadService, LogDefault logDefault) {
        this.bulkTermDownloadService = bulkTermDownloadService;
        this.logDefault = logDefault;
    }


    @PostMapping("/bulk-term/download")
    public ResponseEntity<String> downloadBulkTerm(@RequestBody List<BulkTermExcelDataDTO> bulkTermExcelDataDTOList, HttpServletResponse response) throws IOException {
        logDefault.logCurrentMethod();
        return ResponseEntity.ok(bulkTermDownloadService.saveTermDataToExcel(bulkTermExcelDataDTOList, response));
    }
}
