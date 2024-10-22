package com.dms.datamodelmanagementserver.standardData.controller;

import com.dms.datamodelmanagementserver.global.ApiRequestBuilder;
import com.dms.datamodelmanagementserver.global.DataType;
import com.dms.datamodelmanagementserver.global.DomainType;
import com.dms.datamodelmanagementserver.global.LogDefault;
import com.dms.datamodelmanagementserver.standardArea.dto.StandardAreaDTO;
import com.dms.datamodelmanagementserver.standardArea.service.StandardAreaSelectListService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/dms/standardData")
public class StandardDataPageController {
    private final LogDefault logDefault;
    private final StandardAreaSelectListService standardAreaSelectListService;

    public StandardDataPageController(LogDefault logDefault, StandardAreaSelectListService standardAreaSelectListService, ApiRequestBuilder<String> apiRequestBuilder) {
        this.logDefault = logDefault;
        this.standardAreaSelectListService = standardAreaSelectListService;
    }

    @GetMapping(value = "/page")
    public String standardDataPage(Model model, HttpSession session, HttpServletRequest request) {
        logDefault.logCurrentMethod();
        List<StandardAreaDTO> stdList = standardAreaSelectListService.selectList(request);
        session.setAttribute("stdList", stdList);
        model.addAttribute("domainTypes", Arrays.stream(DomainType.values())
                .map(Enum::toString)
                .collect(Collectors.toList()));
        model.addAttribute("dataTypes", Arrays.stream(DataType.values())
                .map(Enum::toString)
                .collect(Collectors.toList()));
        return "/standardData/standardData";
    }


}
