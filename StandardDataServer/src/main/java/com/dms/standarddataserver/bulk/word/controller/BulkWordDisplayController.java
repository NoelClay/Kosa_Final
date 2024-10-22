package com.dms.standarddataserver.bulk.word.controller;

import com.dms.standarddataserver.bulk.word.dto.WordExcelDataDTO;
import com.dms.standarddataserver.bulk.word.service.BulkWordDisplayService;
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
public class BulkWordDisplayController {

    private final BulkWordDisplayService bulkWordDisplayService;
    private final LogDefault logDefault;

    @Autowired
    public BulkWordDisplayController(BulkWordDisplayService bulkWordDisplayService, LogDefault logDefault) {
        this.bulkWordDisplayService = bulkWordDisplayService;
        this.logDefault = logDefault;
    }

    @PostMapping("/bulk-word/validate")
    private ResponseEntity<?> validateBulkWord(@RequestBody List<WordExcelDataDTO> wordExcelDataDTOList) {
        logDefault.logCurrentMethod();
        List<WordExcelDataDTO> wordExcelDataDTOs = bulkWordDisplayService.validateBulkWord(wordExcelDataDTOList);
        return ResponseEntity.status(HttpStatus.OK).body(wordExcelDataDTOs);
    }
}
