package com.dms.datamodelmanagementserver.single.domain.controller;


import com.dms.datamodelmanagementserver.global.LogDefault;
import com.dms.datamodelmanagementserver.single.domain.dto.DomainDTO;
import com.dms.datamodelmanagementserver.single.domain.service.DomainDeleteService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping(value = "/dms")
public class DomainDeleteController {
    private final LogDefault logDefault;
    private final DomainDeleteService domainDeleteService;

    public DomainDeleteController(LogDefault logDefault, DomainDeleteService domainDeleteService) {
        this.logDefault = logDefault;
        this.domainDeleteService = domainDeleteService;
    }

    @PostMapping(value = "/single-domain/deleteRest")
    @ResponseBody
    public Map<String, Boolean> deleteDomain(@RequestBody DomainDTO domainDTO) {
        logDefault.logCurrentMethod();
        boolean isDomainDeleted = domainDeleteService.deleteDomain(domainDTO);
        Map<String, Boolean> response = new HashMap<>();
        response.put("isDomainDeleted", isDomainDeleted);
        return response;
    }
    
}