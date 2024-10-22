package com.dms.datamodelmanagementserver.standardArea.controller;

import com.dms.datamodelmanagementserver.global.LogDefault;
import com.dms.datamodelmanagementserver.standardArea.dto.StandardAreaDTO;
import com.dms.datamodelmanagementserver.standardArea.service.StandardAreaSelectListService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/dms/standardArea")
public class StandardAreaPageController {
    private final LogDefault logDefault;
    private final StandardAreaSelectListService standardAreaSelectListService;

    public StandardAreaPageController(LogDefault logDefault, StandardAreaSelectListService standardAreaSelectListService) {
        this.logDefault = logDefault;
        this.standardAreaSelectListService = standardAreaSelectListService;
    }

    @GetMapping(value = "/page")
    public String standardAreaInsertPage(HttpSession session, HttpServletRequest request) {
        logDefault.logCurrentMethod();
        List<StandardAreaDTO> stdList = standardAreaSelectListService.selectList(request);
        session.setAttribute("stdList", stdList);
        return "/standardArea/standardAreaInsert";
    }
}
