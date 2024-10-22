package com.dms.datamodelmanagementserver.standardArea.controller;

import com.dms.datamodelmanagementserver.global.LogDefault;
import com.dms.datamodelmanagementserver.standardArea.dto.StandardAreaDTO;
import com.dms.datamodelmanagementserver.standardArea.service.StandardAreaSelectListService;
import com.dms.datamodelmanagementserver.standardArea.service.StandardAreaUpdateService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Slf4j
@RequestMapping(value = "/dms/standardArea")
public class StandardAreaUpdateController {
    private final StandardAreaUpdateService standardAreaUpdateService;
    private final StandardAreaSelectListService standardAreaSelectListService;
    private final LogDefault logDefault;

    public StandardAreaUpdateController(StandardAreaUpdateService standardAreaUpdateService, StandardAreaSelectListService standardAreaSelectListService, LogDefault logDefault) {
        this.standardAreaUpdateService = standardAreaUpdateService;
        this.standardAreaSelectListService = standardAreaSelectListService;
        this.logDefault = logDefault;
    }

    @PostMapping(value = "/update")
    @ResponseBody
    public Map<String, Boolean> updateStandardArea(@RequestBody StandardAreaDTO standardAreaDTO, HttpSession session, HttpServletRequest request) {
        logDefault.logCurrentMethod();
        boolean isDuplicate = standardAreaUpdateService.update(standardAreaDTO);
        Map<String, Boolean> response = new HashMap<>();
        response.put("isDuplicate", isDuplicate);
        List<StandardAreaDTO> stdList = standardAreaSelectListService.selectList(request);
        session.setAttribute("stdList", stdList);
        return response;
    }
}