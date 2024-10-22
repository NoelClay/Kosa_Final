package com.dms.datamodelmanagementserver.single.domain.controller;

import com.dms.datamodelmanagementserver.global.LogDefault;
import com.dms.datamodelmanagementserver.single.domain.dto.DomainDTO;
import com.dms.datamodelmanagementserver.single.domain.service.DomainUpdateService;
import jakarta.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/dms")
public class DomainUpdateController {

    private final DomainUpdateService domainUpdateService;
    private final LogDefault logDefault;

    public DomainUpdateController(DomainUpdateService domainUpdateService, LogDefault logDefault) {
		super();
		this.domainUpdateService = domainUpdateService;
		this.logDefault = logDefault;
	}

	@PostMapping("/single-domain/updateRest")
    @ResponseBody
    public Map<String, Object> singleDomainUpdateRest(@RequestBody DomainDTO domainDTO, HttpServletResponse response, Model model) {
        logDefault.logCurrentMethod();
        Map<String, Object> map = new HashMap<>();
        DomainDTO result = domainUpdateService.singleDomainUpdateRest(domainDTO);
        if (result != null) {
        	map.put("domainDTO", result);
        }
        return map;
    }

}
