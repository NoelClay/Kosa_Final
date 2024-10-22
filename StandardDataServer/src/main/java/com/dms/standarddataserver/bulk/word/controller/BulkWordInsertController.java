package com.dms.standarddataserver.bulk.word.controller;

import com.dms.standarddataserver.bulk.word.dto.BulkWordDataDTO;
import com.dms.standarddataserver.bulk.word.service.BulkWordInsertService;
import com.dms.standarddataserver.global.LogDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/std")
public class BulkWordInsertController {

    private final BulkWordInsertService bulkWordInsertService;
    private final LogDefault logDefault;

    public BulkWordInsertController(BulkWordInsertService bulkWordInsertService, LogDefault logDefault) {
        this.bulkWordInsertService = bulkWordInsertService;
        this.logDefault = logDefault;
    }

    @PostMapping("/bulk-word/insert")
    private ResponseEntity<?> insertBulkWord(@RequestBody List<BulkWordDataDTO> bulkWordDataDTOList) {
        logDefault.logCurrentMethod();
        List<BulkWordDataDTO> bulkWordDataDTOS = bulkWordInsertService.insertBulkWord(bulkWordDataDTOList);
        return ResponseEntity.status(HttpStatus.OK).body(bulkWordDataDTOS);
    }
}
