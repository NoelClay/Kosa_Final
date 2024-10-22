package com.dms.datamodelmanagementserver.standardArea.controller;

import com.dms.datamodelmanagementserver.global.LogDefault;
import com.dms.datamodelmanagementserver.standardArea.dto.StandardAreaDTO;
import com.dms.datamodelmanagementserver.standardArea.service.StandardAreaSelectListService;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/dms/standardArea")
public class StandardAreaSelectListController {
    private final StandardAreaSelectListService standardAreaSelectListService;
    private final LogDefault logDefault;

    public StandardAreaSelectListController(StandardAreaSelectListService standardAreaSelectListService, LogDefault logDefault) {
        this.standardAreaSelectListService = standardAreaSelectListService;
        this.logDefault = logDefault;
    }

    @PostMapping(value = "/selectList")
    @ResponseBody
    public ResponseEntity<List<StandardAreaDTO>> selectList(HttpServletRequest request) {
        logDefault.logCurrentMethod();
        return ResponseEntity.status(HttpStatus.OK).body(standardAreaSelectListService.selectList(request));
    }
}
