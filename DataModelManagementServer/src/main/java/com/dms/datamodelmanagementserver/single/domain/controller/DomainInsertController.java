package com.dms.datamodelmanagementserver.single.domain.controller;

import com.dms.datamodelmanagementserver.global.LogDefault;

import com.dms.datamodelmanagementserver.single.domain.dto.DomainDTO;
import com.dms.datamodelmanagementserver.single.domain.service.DomainInsertService;
import com.dms.datamodelmanagementserver.standardArea.service.StandardAreaSelectListService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;


import java.util.HashMap;

import java.util.Map;
import java.util.UUID;

@Slf4j
@Controller
@RequestMapping("/dms")
public class DomainInsertController {

    private final DomainInsertService domainInsertService;

    private final StandardAreaSelectListService standardAreaSelectListService;
    private final LogDefault logDefault;

    public DomainInsertController(DomainInsertService domainInsertService, StandardAreaSelectListService standardAreaSelectListService,LogDefault logDefault) {
        this.domainInsertService = domainInsertService;
        this.standardAreaSelectListService = standardAreaSelectListService;
        this.logDefault = logDefault;
    }

    @PostMapping("/single-domain/insertRest")
    @ResponseBody

    public Map<String, Boolean> singleWordInsertRest(@RequestBody DomainDTO domainDTO) {
        logDefault.logCurrentMethod();
        log.info("DMS::INSERT CONTROLLER" + domainDTO);

        boolean isDomainInserted = domainInsertService.singleDomainInsertRest(domainDTO);

        Map<String, Boolean> response = new HashMap<>();
        response.put("isDomainInserted", isDomainInserted);
        log.info("DMS::INSERT CONTROLLER END");

        return response;
    }

}