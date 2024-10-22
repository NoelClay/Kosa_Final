package com.dms.standarddataserver.bulk.term.controller;

import com.dms.standarddataserver.bulk.term.dto.BulkTermExcelDataDTO;
import com.dms.standarddataserver.bulk.term.service.BulkTermDisplayService;
import com.dms.standarddataserver.global.LogDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/std")
public class BulkTermDisplayController {

    private final BulkTermDisplayService bulkTermDisplayService;
    private final LogDefault logDefault;

    public BulkTermDisplayController(BulkTermDisplayService bulkTermDisplayService, LogDefault logDefault) {
        this.bulkTermDisplayService = bulkTermDisplayService;
        this.logDefault = logDefault;
    }

    @PostMapping("/bulk-term/validate")
    private ResponseEntity<?> validateBulkTerm(@RequestParam("file") MultipartFile file, @RequestParam("stdAreaName") String stdAreaName) {
        logDefault.logCurrentMethod();
        List<BulkTermExcelDataDTO> bulkTermExcelDataDTOs = bulkTermDisplayService.validateBulkTerm(file, stdAreaName);
        return ResponseEntity.status(HttpStatus.OK).body(bulkTermExcelDataDTOs);
    }
}
