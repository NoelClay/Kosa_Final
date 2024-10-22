package com.dms.datamodelmanagementserver.bulk.word.controller;

import com.dms.datamodelmanagementserver.bulk.word.dto.WordExcelDataDTO;
import com.dms.datamodelmanagementserver.bulk.word.service.BulkWordDisplayService;
import com.dms.datamodelmanagementserver.global.LogDefault;
import com.dms.datamodelmanagementserver.standardArea.dto.StandardAreaDTO;
import com.dms.datamodelmanagementserver.standardArea.service.StandardAreaSelectListService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/dms")
public class BulkWordDisplayController {

    private final BulkWordDisplayService bulkWordDisplayService;
    private final StandardAreaSelectListService standardAreaSelectListService;
    private final LogDefault logDefault;

    public BulkWordDisplayController(BulkWordDisplayService bulkWordDisplayService, StandardAreaSelectListService standardAreaSelectListService, LogDefault logDefault) {
        this.bulkWordDisplayService = bulkWordDisplayService;
        this.standardAreaSelectListService = standardAreaSelectListService;
        this.logDefault = logDefault;
    }

    @GetMapping("/bulk-word")
    public String getForm(HttpSession session, HttpServletRequest request) {
        logDefault.logCurrentMethod();
        List<StandardAreaDTO> stdList = standardAreaSelectListService.selectList(request);
        session.setAttribute("stdList",stdList);
        return "/bulk/bulk-word";
    }

    @PostMapping("/bulk-word/display")
    public String readWordExcelData(@RequestParam("file") MultipartFile file,
                                    @RequestParam("stdAreaName") String stdAreaName,
                                    Model model, HttpSession session, HttpServletRequest request) {
        logDefault.logCurrentMethod();

        List<StandardAreaDTO> stdList = standardAreaSelectListService.selectList(request);
        List<WordExcelDataDTO> wordExcelDataDTOList = bulkWordDisplayService.readWordExcelData(file, stdAreaName);

        session.setAttribute("stdList",stdList);
        model.addAttribute("wordExcelDataDTOList", wordExcelDataDTOList);
        return "/bulk/bulk-word";
    }
}
