package com.dms.datamodelmanagementserver.single.term.controller;

import com.dms.datamodelmanagementserver.single.term.dto.TermDomainDTO;
import com.dms.datamodelmanagementserver.single.term.service.TermInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/dms")
public class TermInfoController {

    private final TermInfoService  termInfoService;

    public TermInfoController(TermInfoService termInfoService) {
        this.termInfoService = termInfoService;
    }


    @PostMapping("/single-wordAndTerm/selectTermRest")
    @ResponseBody
    public Map<String, Object> getTermInfoById(@RequestBody Map<String, String> requestMap) {

        String dicId = requestMap.get("dicId");

        log.info("DMS :: TERMInfoController= START = " + dicId );
        TermDomainDTO receivedTermDTO = termInfoService.getTermInfoRest(dicId);    
        Map<String, Object> response = new HashMap<>();
        response.put("receivedTermDTO", receivedTermDTO);
        log.info("DMS :: WordInfoController= END = " + receivedTermDTO);

        return response;

    }

}
