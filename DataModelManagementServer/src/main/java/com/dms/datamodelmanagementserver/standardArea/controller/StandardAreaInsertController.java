package com.dms.datamodelmanagementserver.standardArea.controller;

import com.dms.datamodelmanagementserver.global.LogDefault;
import com.dms.datamodelmanagementserver.standardArea.dto.StandardAreaDTO;
import com.dms.datamodelmanagementserver.standardArea.service.StandardAreaInsertService;
import com.dms.datamodelmanagementserver.standardArea.service.StandardAreaSelectListService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@Slf4j
@RequestMapping("/dms/standardArea")
public class StandardAreaInsertController {
    private final StandardAreaInsertService standardAreaInsertService;
    private final StandardAreaSelectListService standardAreaSelectListService;
    private final LogDefault logDefault;

    public StandardAreaInsertController(StandardAreaInsertService standardAreaInsertService, StandardAreaSelectListService standardAreaSelectListService, LogDefault logDefault) {
        this.standardAreaInsertService = standardAreaInsertService;
        this.standardAreaSelectListService = standardAreaSelectListService;
        this.logDefault = logDefault;
    }

    @PostMapping(value = "/insert")
    @ResponseBody
    public Map<String, Boolean> insertStandardArea(@RequestBody StandardAreaDTO standardAreaDTO, HttpSession session, HttpServletRequest request) {
        logDefault.logCurrentMethod();
        boolean isDuplicate = standardAreaInsertService.insert(standardAreaDTO, request);
        Map<String, Boolean> response = new HashMap<>();
        response.put("isDuplicate", isDuplicate);
        List<StandardAreaDTO> stdList = standardAreaSelectListService.selectList(request);
        session.setAttribute("stdList", stdList);
        return response;
    }
}
