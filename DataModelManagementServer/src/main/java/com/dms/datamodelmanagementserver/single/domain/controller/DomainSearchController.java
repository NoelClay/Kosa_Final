package com.dms.datamodelmanagementserver.single.domain.controller;

import com.dms.datamodelmanagementserver.single.domain.dto.DomainDTO;
import com.dms.datamodelmanagementserver.single.domain.service.DomainSearchService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/dms")
public class DomainSearchController {

    private final DomainSearchService domainSearchService;

    public DomainSearchController(DomainSearchService domainSearchService) {
        this.domainSearchService = domainSearchService;
    }

    @PostMapping("/single-domain/domainSearchRest")
    @ResponseBody
    public Map<String, Object> getDomainListRest(@RequestBody Map<String, String> requestMap) {

        String stdAreaName = requestMap.get("stdAreaName");
        String domainName = requestMap.get("domainName");
        String keyDomName = requestMap.get("keyDomName");

        List<DomainDTO> domainList = domainSearchService.searchDomainNameByDomainNameRest(stdAreaName, domainName, keyDomName);
        Map<String, Object> response = new HashMap<>();
        response.put("domainList", domainList);

        return response;
    }
    
}
