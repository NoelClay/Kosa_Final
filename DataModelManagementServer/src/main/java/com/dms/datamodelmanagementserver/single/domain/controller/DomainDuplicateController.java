package com.dms.datamodelmanagementserver.single.domain.controller;

import com.dms.datamodelmanagementserver.global.LogDefault;
import com.dms.datamodelmanagementserver.global.UrlBuilder;
import com.dms.datamodelmanagementserver.single.domain.dto.DomainDTO;
import com.dms.datamodelmanagementserver.single.domain.dto.DomainGroupDTO;
import com.dms.datamodelmanagementserver.single.domain.service.DomainDuplicateService;

import jakarta.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/dms")
public class DomainDuplicateController {

    private final DomainDuplicateService domainDuplicateService;
    private final UrlBuilder urlBuilder;

    private final LogDefault logDefault;


    public DomainDuplicateController(DomainDuplicateService domainDuplicateService, UrlBuilder urlBuilder,LogDefault logDefault) {
        this.domainDuplicateService = domainDuplicateService;
        this.urlBuilder = urlBuilder;
        this.logDefault=logDefault;
    }

    @PostMapping("/single-domain/duplicateDomainRest")
    @ResponseBody
    public Map<String, Boolean> isDuplicateDomainRest(@RequestBody DomainDTO domainDTO) {
    	logDefault.logCurrentMethod();
        boolean isDuplicate = domainDuplicateService.isDuplicateDomainRest(domainDTO);
        Map<String, Boolean> response = new HashMap<>();
        response.put("isDuplicate", isDuplicate);
        return response;
    }

    // 도메인 그룹 등록 기능 구현 시 필요
    @PostMapping("/single-domain/duplicateDomainGroupRest")
    @ResponseBody
    public boolean isDuplicateDomainGroupRest(@RequestBody DomainGroupDTO domainGroupDTO, HttpServletResponse response) {
    	logDefault.logCurrentMethod();
        boolean isDuplicate = domainDuplicateService.isDuplicateDomainGroupRest(domainGroupDTO);
        return isDuplicate;
    }
}