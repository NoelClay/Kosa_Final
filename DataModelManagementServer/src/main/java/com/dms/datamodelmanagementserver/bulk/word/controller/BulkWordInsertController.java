package com.dms.datamodelmanagementserver.bulk.word.controller;

import com.dms.datamodelmanagementserver.bulk.word.dto.BulkWordDataDTO;
import com.dms.datamodelmanagementserver.bulk.word.service.BulkWordInsertService;
import com.dms.datamodelmanagementserver.global.LogDefault;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/dms")
public class BulkWordInsertController {

    private final BulkWordInsertService bulkWordInsertService;
    private final LogDefault logDefault;

    @Autowired
    public BulkWordInsertController(BulkWordInsertService bulkWordInsertService, LogDefault logDefault) {
        this.bulkWordInsertService = bulkWordInsertService;
        this.logDefault = logDefault;
    }

    @PostMapping("/bulk-word/insert")
    @ResponseBody
    public List<BulkWordDataDTO> insertBulkWord(@RequestBody List<BulkWordDataDTO> bulkWordDataDTOList) {
        logDefault.logCurrentMethod();
        List<BulkWordDataDTO> insertedWordDataList = bulkWordInsertService.insertBulkWord(bulkWordDataDTOList);
        return insertedWordDataList;
    }

}
