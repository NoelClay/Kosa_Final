package com.dms.standarddataserver.domainGroup.controller;

import com.dms.standarddataserver.bulk.domain.dto.DomainExcelDataDTO;
import com.dms.standarddataserver.bulk.domain.service.BulkDomainInsertService;
import com.dms.standarddataserver.domainGroup.dto.DomainGroupDTO;
import com.dms.standarddataserver.domainGroup.service.DomainGroupInsertService;
import com.dms.standarddataserver.global.LogDefault;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/std")
public class DomainGroupInsertController {

    private final DomainGroupInsertService domainGroupInsertService;
    private final LogDefault logDefault;
    
    public DomainGroupInsertController(DomainGroupInsertService domainGroupInsertService, LogDefault logDefault) {
		this.domainGroupInsertService = domainGroupInsertService;
		this.logDefault = logDefault;
	}

    @PostMapping("/domain-group/insert")
    private ResponseEntity<?> insertBulkDomain(@RequestBody DomainGroupDTO domainGroupDto) {
        logDefault.logCurrentMethod();
        // 여기 임의임
        int result = 1;
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
