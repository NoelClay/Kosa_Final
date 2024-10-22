package com.dms.datamodelmanagementserver.single.domain.controller;


import com.dms.datamodelmanagementserver.global.LogDefault;
import com.dms.datamodelmanagementserver.single.domain.service.DomainGroupService;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/dms")
public class DomainGroupController {

    private final DomainGroupService domainGroupService;
    private final LogDefault logDefault;
    
    public DomainGroupController(DomainGroupService domainGroupService, LogDefault logDefault) {
		super();
		this.domainGroupService = domainGroupService;
		this.logDefault = logDefault;
	}

	// 프로젝트별 도메인 그룹 조회 - getDomainGroup()
    @PostMapping("/single-domain/group")
    @ResponseBody
    public Map<String, Object> domainGroup(HttpServletRequest request) {
        logDefault.logCurrentMethod();
        Map<String, Object> response = new HashMap<>();
        response.put("domainGroupDTO", domainGroupService.getDomainGroup(request));
        return response;
    }

}