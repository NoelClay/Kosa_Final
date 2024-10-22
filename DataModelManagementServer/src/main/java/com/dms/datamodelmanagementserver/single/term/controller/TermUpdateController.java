package com.dms.datamodelmanagementserver.single.term.controller;

import com.dms.datamodelmanagementserver.single.term.service.TermUpdateService;
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
public class TermUpdateController {

    private final TermUpdateService termUpdateService;

    public TermUpdateController(TermUpdateService termUpdateService) {
        this.termUpdateService = termUpdateService;
    }

    @PostMapping("/single-term/updateTermRest")
    @ResponseBody
    public Map<String, Object> updateTermRest(@RequestBody Map<String, String> requestMap) {

        String dicId = requestMap.get("dicId");
        String domId = requestMap.get("domId");
        String dicDesc = requestMap.get("dicDesc");

        log.info("DMS ::: TERMUPDATECONTROLLER dicId = " + dicId);
        log.info("DMS ::: TERMUPDATECONTROLLER domId = " + domId);



        boolean isTermUpdated = termUpdateService.updateTermRest(dicId, domId, dicDesc);

        Map<String, Object> response = new HashMap<>();

        response.put("isTermUpdated", isTermUpdated);
        
        // 용어 수정 후, 용어를 다시 조회 
        response.put("dicId", dicId);

        return response;

    }

}
