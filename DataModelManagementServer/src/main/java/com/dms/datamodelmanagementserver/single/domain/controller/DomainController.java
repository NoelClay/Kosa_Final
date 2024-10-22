package com.dms.datamodelmanagementserver.single.domain.controller;


import com.dms.datamodelmanagementserver.global.LogDefault;
import com.dms.datamodelmanagementserver.standardArea.dto.StandardAreaDTO;
import com.dms.datamodelmanagementserver.standardArea.service.StandardAreaSelectListService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/dms")
public class DomainController {

    private final StandardAreaSelectListService standardAreaSelectListService;
    private final LogDefault logDefault;
    public DomainController(StandardAreaSelectListService standardAreaSelectListService, LogDefault logDefault) {
        this.standardAreaSelectListService = standardAreaSelectListService;
        this.logDefault = logDefault;
    }

    @GetMapping("/single-domain/form")
    public String domainInsertForm(Model model, HttpSession session, HttpServletRequest request) {
        logDefault.logCurrentMethod();
        List<StandardAreaDTO> stdList = standardAreaSelectListService.selectList(request);//추가
        session.setAttribute("stdList",stdList);//추가
//        model.addAttribute("stdList", stdList);
        return "/domain/domainInsertPage";
    }

}