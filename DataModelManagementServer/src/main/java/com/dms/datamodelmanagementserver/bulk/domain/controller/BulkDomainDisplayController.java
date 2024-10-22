package com.dms.datamodelmanagementserver.bulk.domain.controller;

import com.dms.datamodelmanagementserver.bulk.domain.dto.DomainExcelDataDTO;
import com.dms.datamodelmanagementserver.bulk.domain.service.BulkDomainDisplayService;
import com.dms.datamodelmanagementserver.global.LogDefault;
import com.dms.datamodelmanagementserver.standardArea.dto.StandardAreaDTO;
import com.dms.datamodelmanagementserver.standardArea.service.StandardAreaSelectListService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/dms")
public class BulkDomainDisplayController {

    private final BulkDomainDisplayService bulkDomainDisplayService;
    private final StandardAreaSelectListService standardAreaSelectListService;
    private final LogDefault logDefault;

    public BulkDomainDisplayController(BulkDomainDisplayService bulkDomainDisplayService, StandardAreaSelectListService standardAreaSelectListService, LogDefault logDefault) {
        this.bulkDomainDisplayService = bulkDomainDisplayService;
        this.standardAreaSelectListService = standardAreaSelectListService;
        this.logDefault = logDefault;
    }

    @GetMapping("/bulk-domain")
    public String getForm(HttpSession session, HttpServletRequest request) {
        logDefault.logCurrentMethod();
        List<StandardAreaDTO> stdList = standardAreaSelectListService.selectList(request);
        session.setAttribute("stdList",stdList);
        return "/bulk/bulk-domain";
    }

    @PostMapping("/bulk-domain/display")
    public String readDomainExcelData(@RequestParam("file") MultipartFile file,
                                      @RequestParam("stdAreaName") String stdAreaName,
                                      Model model, HttpSession session, HttpServletRequest request) {
        logDefault.logCurrentMethod();

        List<StandardAreaDTO> stdList = standardAreaSelectListService.selectList(request);
        List<DomainExcelDataDTO> domainExcelDataList = bulkDomainDisplayService.readDomainExcelData(file, stdAreaName);

        session.setAttribute("stdList",stdList);
        model.addAttribute("domainExcelDataList", domainExcelDataList);
        return "/bulk/bulk-domain";
    }
}
