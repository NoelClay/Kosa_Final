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
@RequestMapping(value = "/dms/standardArea")
public class StandardAreaManagementPageController {
    private final LogDefault logDefault;
    private final StandardAreaSelectListService standardAreaSelectListService;

    public StandardAreaManagementPageController(LogDefault logDefault, StandardAreaSelectListService standardAreaSelectListService) {
        this.logDefault = logDefault;
        this.standardAreaSelectListService = standardAreaSelectListService;
    }

    @GetMapping(value = "/managementPage")
    public String standardAreaManagementPage(HttpSession session, HttpServletRequest request) {
        logDefault.logCurrentMethod();
        List<StandardAreaDTO> stdList = standardAreaSelectListService.selectList(request);
        session.setAttribute("stdList",stdList);
        return "/standardArea/standardAreaManagement";
    }
}
