package com.dms.datamodelmanagementserver.single.domain.controller;

import com.dms.datamodelmanagementserver.global.LogDefault;
import com.dms.datamodelmanagementserver.single.domain.dto.DomainDTO;
import com.dms.datamodelmanagementserver.single.domain.service.DomainInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/dms")
public class DomainInfoController {

    private final DomainInfoService domainInfoService;

    private final LogDefault logDefault;

    public DomainInfoController(DomainInfoService domainInfoService, LogDefault logDefault) {
        this.domainInfoService = domainInfoService;
        this.logDefault = logDefault;
    }

    @PostMapping("/single-domain/selectRest")
    @ResponseBody
    public Map<String, Object> getDomainInfoByDomId(@RequestBody Map<String, String> requestMap) {
        logDefault.logCurrentMethod();

        String domId = requestMap.get("domId");
        DomainDTO receivedDomainDTO = domainInfoService.getDomainInfoRest(domId);

        Map<String, Object> response = new HashMap<>();
        response.put("receivedDomainDTO", receivedDomainDTO);

        return response;
    }

}