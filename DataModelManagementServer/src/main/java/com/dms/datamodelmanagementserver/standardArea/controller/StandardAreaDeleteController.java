package com.dms.datamodelmanagementserver.standardArea.controller;

import com.dms.datamodelmanagementserver.global.LogDefault;
import com.dms.datamodelmanagementserver.standardArea.dto.StandardAreaDTO;
import com.dms.datamodelmanagementserver.standardArea.service.StandardAreaDeleteService;
import com.dms.datamodelmanagementserver.standardArea.service.StandardAreaSelectListService;

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
public class StandardAreaDeleteController {
    private final LogDefault logDefault;
    private final StandardAreaDeleteService standardAreaDeleteService;
    private final StandardAreaSelectListService standardAreaSelectListService;


    public StandardAreaDeleteController(LogDefault logDefault, StandardAreaDeleteService standardAreaDeleteService, StandardAreaSelectListService standardAreaSelectListService) {
        this.logDefault = logDefault;
        this.standardAreaDeleteService = standardAreaDeleteService;
        this.standardAreaSelectListService = standardAreaSelectListService;
    }

    @PostMapping(value = "/delete")
    @ResponseBody
    public Map<String, Boolean> delete(@RequestBody StandardAreaDTO standardAreaDTO, HttpSession session, HttpServletRequest request) {
        logDefault.logCurrentMethod();
        log.info(standardAreaDTO.getStdAreaNm());
        boolean result = standardAreaDeleteService.delete(standardAreaDTO);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleteResult", result);
        List<StandardAreaDTO> stdList = standardAreaSelectListService.selectList(request);
        session.setAttribute("stdList", stdList);

        return response;
    }
}
