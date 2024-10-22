package com.dms.standarddataserver.bulk.term.controller;

import com.dms.standarddataserver.bulk.term.dto.BulkTermDataDTO;
import com.dms.standarddataserver.bulk.term.service.BulkTermInsertService;
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
public class BulkTermInsertController {

    private final BulkTermInsertService bulkTermInsertService;
    private final LogDefault logDefault;

    public BulkTermInsertController(BulkTermInsertService bulkTermInsertService, LogDefault logDefault) {
        this.bulkTermInsertService = bulkTermInsertService;
        this.logDefault = logDefault;
    }


    @PostMapping("/bulk-term/insert")
    private ResponseEntity<?> insertBulkTerm(@RequestBody List<BulkTermDataDTO> bulkTermDataDTOList) {
        logDefault.logCurrentMethod();
        List<BulkTermDataDTO> result = bulkTermInsertService.insertBulkTerm(bulkTermDataDTOList);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
