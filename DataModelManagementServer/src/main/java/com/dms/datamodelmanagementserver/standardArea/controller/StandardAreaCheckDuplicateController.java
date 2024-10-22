package com.dms.datamodelmanagementserver.standardArea.controller;


import com.dms.datamodelmanagementserver.global.LogDefault;
import com.dms.datamodelmanagementserver.standardArea.service.StandardAreaCheckDuplicateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@Slf4j
@RequestMapping("/dms/standardArea")
public class StandardAreaCheckDuplicateController {

    private final StandardAreaCheckDuplicateService standardAreaCheckDuplicateService;
    private final LogDefault logDefault;


    public StandardAreaCheckDuplicateController(StandardAreaCheckDuplicateService standardAreaCheckDuplicateService, LogDefault logDefault) {
        this.standardAreaCheckDuplicateService = standardAreaCheckDuplicateService;
        this.logDefault = logDefault;
    }

    @PostMapping(value = "/checkDuplicate")
    @ResponseBody
    public Map<String, Boolean> checkDuplicate(@RequestBody Map<String, String> requestBody) {
        logDefault.logCurrentMethod(

        );
        String standardAreaName = requestBody.get("standardAreaName");
        boolean isDuplicate = standardAreaCheckDuplicateService.checkIfDuplicate(standardAreaName);
        Map<String, Boolean> response = new HashMap<>();
        response.put("isDuplicate", isDuplicate);
        return response;

    }
}
