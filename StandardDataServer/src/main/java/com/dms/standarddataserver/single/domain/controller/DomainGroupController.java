package com.dms.standarddataserver.single.domain.controller;

import com.dms.standarddataserver.global.LogDefault;
import com.dms.standarddataserver.single.domain.dto.DomainGroupDTO;
import com.dms.standarddataserver.single.domain.service.DomainGroupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/std")
public class DomainGroupController {
    private static final Logger logger = LoggerFactory.getLogger(DomainGroupController.class);

    private final DomainGroupService domainGroupService;

    private final LogDefault logDefault;



    @Autowired
    public DomainGroupController(DomainGroupService domainGroupService,LogDefault logDefault) {
        this.domainGroupService = domainGroupService;
        this.logDefault = logDefault;
    }

    @PostMapping ("/single-domain/group")
    public ResponseEntity<List<DomainGroupDTO>> domainGroup(@RequestBody String subjAreaNameCookieValue) {
        logDefault.logCurrentMethod();
        return ResponseEntity.status(HttpStatus.OK).body(domainGroupService.getDomainGroup(subjAreaNameCookieValue));
    }
}