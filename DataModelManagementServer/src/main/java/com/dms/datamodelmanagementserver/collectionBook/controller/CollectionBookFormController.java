package com.dms.datamodelmanagementserver.collectionBook.controller;

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
@RequestMapping("/dms/collectionBook")
public class CollectionBookFormController {
    private final LogDefault logDefault;
    private final StandardAreaSelectListService standardAreaSelectListService;

    public CollectionBookFormController(LogDefault logDefault, StandardAreaSelectListService standardAreaSelectListService) {
        this.logDefault = logDefault;
        this.standardAreaSelectListService = standardAreaSelectListService;
    }

    @GetMapping("/page")
    public String getForm(HttpSession session, HttpServletRequest request) {
        logDefault.logCurrentMethod();
        List<StandardAreaDTO> stdList = standardAreaSelectListService.selectList(request);
        if(stdList == null) {
        	return "/member/auth-login-basic";
        }

        session.setAttribute("stdList",stdList);
        return "/collectionBook/collectionBook";
    }
}
