package com.dms.datamodelmanagementserver.bulk.term.controller;

import com.dms.datamodelmanagementserver.bulk.term.dto.BulkTermExcelDataDTO;
import com.dms.datamodelmanagementserver.bulk.term.service.BulkTermDisplayService;
import com.dms.datamodelmanagementserver.global.LogDefault;
import com.dms.datamodelmanagementserver.standardArea.dto.StandardAreaDTO;
import com.dms.datamodelmanagementserver.standardArea.service.StandardAreaSelectListService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/dms")
public class BulkTermDisplayController {

    private final BulkTermDisplayService bulkTermDisplayService;
    private final StandardAreaSelectListService standardAreaSelectListService;
    private final LogDefault logDefault;

    public BulkTermDisplayController(BulkTermDisplayService bulkTermDisplayService, StandardAreaSelectListService standardAreaSelectListService, LogDefault logDefault) {
        this.bulkTermDisplayService = bulkTermDisplayService;
        this.standardAreaSelectListService = standardAreaSelectListService;
        this.logDefault = logDefault;
    }

    @GetMapping("/bulk-term")
    public String getForm(HttpSession session, HttpServletRequest request) {
        logDefault.logCurrentMethod();
        List<StandardAreaDTO> stdList = standardAreaSelectListService.selectList(request);
        session.setAttribute("stdList",stdList);
        return "/bulk/bulk-term";
    }
    
    @GetMapping("/test")
    public String form() {
    	return "/bulk/bulk-term";
    }

    @PostMapping("/bulk-term/display")
    public String readDomainExcelData(@RequestParam("file") MultipartFile file,
                                      @RequestParam("stdAreaName") String stdAreaName,
                                      Model model, HttpSession session, HttpServletRequest request) {
        logDefault.logCurrentMethod();

        List<StandardAreaDTO> stdList = standardAreaSelectListService.selectList(request);
        List<BulkTermExcelDataDTO> termExcelDataDTOList = bulkTermDisplayService.readTermExcelData(file, stdAreaName);

        session.setAttribute("stdList",stdList);
        model.addAttribute("termExcelDataDTOList", termExcelDataDTOList);
        return "/bulk/bulk-term";
    }
}
