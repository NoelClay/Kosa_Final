package com.dms.datamodelmanagementserver.bulk.term.controller;

import com.dms.datamodelmanagementserver.bulk.term.dto.BulkTermDataDTO;
import com.dms.datamodelmanagementserver.bulk.term.service.BulkTermInsertService;
import com.dms.datamodelmanagementserver.global.LogDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/dms")
public class BulkTermInsertController {
    private final BulkTermInsertService bulkWordInsertService;
    private final LogDefault logDefault;

    public BulkTermInsertController(BulkTermInsertService bulkWordInsertService, LogDefault logDefault) {
        this.bulkWordInsertService = bulkWordInsertService;
        this.logDefault = logDefault;
    }

    @PostMapping("/bulk-term/insert")
    @ResponseBody
    public List<BulkTermDataDTO> insertBulkTerm(@RequestBody List<BulkTermDataDTO> bulkTermDataDTOList) {
        logDefault.logCurrentMethod();
        List<BulkTermDataDTO> insertedTermDataList = bulkWordInsertService.insertBulkTerm(bulkTermDataDTOList);
        return insertedTermDataList;
    }

}
