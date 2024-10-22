package com.dms.datamodelmanagementserver.bulk.domain.controller;

import com.dms.datamodelmanagementserver.bulk.domain.dto.DomainExcelDataDTO;
import com.dms.datamodelmanagementserver.bulk.domain.service.BulkDomainInsertService;
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
public class BulkDomainInsertController {

    private final BulkDomainInsertService bulkDomainInsertService;
    private final LogDefault logDefault;

    @Autowired
    public BulkDomainInsertController(BulkDomainInsertService bulkDomainInsertService, LogDefault logDefault) {
        this.bulkDomainInsertService = bulkDomainInsertService;
        this.logDefault = logDefault;
    }

    @PostMapping("/bulk-domain/insert")
    @ResponseBody
    public List<DomainExcelDataDTO> insertBulkDomain(@RequestBody List<DomainExcelDataDTO> domainExcelDataDTOList) {
        logDefault.logCurrentMethod();
        List<DomainExcelDataDTO> insertedDomainDataList = bulkDomainInsertService.insertBulkDomain(domainExcelDataDTOList);
        return insertedDomainDataList;
    }

}
