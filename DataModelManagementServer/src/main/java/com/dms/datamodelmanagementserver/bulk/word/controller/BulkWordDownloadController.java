package com.dms.datamodelmanagementserver.bulk.word.controller;

import com.dms.datamodelmanagementserver.bulk.word.dto.WordExcelDataDTO;
import com.dms.datamodelmanagementserver.bulk.word.service.BulkWordDownloadService;
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
public class BulkWordDownloadController {

    private final BulkWordDownloadService bulkWordDownloadService;
    private final LogDefault logDefault;

    @Autowired
    public BulkWordDownloadController(BulkWordDownloadService bulkWordDownloadService, LogDefault logDefault) {
        this.bulkWordDownloadService = bulkWordDownloadService;
        this.logDefault = logDefault;
    }

    @PostMapping("/bulk-word/download")
    public ResponseEntity<String> downloadBulkWord(@RequestBody List<WordExcelDataDTO> wordExcelDataDTOList, HttpServletResponse response) throws IOException {
        logDefault.logCurrentMethod();
        return ResponseEntity.ok(bulkWordDownloadService.saveWordDataToExcel(wordExcelDataDTOList, response));
    }
}
