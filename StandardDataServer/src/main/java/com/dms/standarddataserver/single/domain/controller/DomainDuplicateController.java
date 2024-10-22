package com.dms.standarddataserver.single.domain.controller;

import com.dms.standarddataserver.global.LogDefault;
import com.dms.standarddataserver.single.domain.dto.DomainDTO;
import com.dms.standarddataserver.single.domain.dto.DomainGroupDTO;
import com.dms.standarddataserver.single.domain.service.DomainDuplicateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/std")
public class DomainDuplicateController {

    private static final Logger logger = LoggerFactory.getLogger(DomainDuplicateController.class);
    private final DomainDuplicateService domainDuplicateService;

    private final LogDefault logDefault;


    @Autowired
    public DomainDuplicateController(DomainDuplicateService domainDuplicateService, LogDefault logDefault) {
        this.domainDuplicateService = domainDuplicateService;
        this.logDefault=logDefault;
    }

    @PostMapping("/single-domain/duplicate")
    public ResponseEntity<Boolean> domainDuplicate(@RequestBody DomainDTO domainDTO) {
        logger.info(this.getClass().getName() + "::STD DUPLICATE CONTROLLER::START");
        logger.info(this.getClass().getName() +"::STD DUPLICATE CONTROLLER" +  domainDTO);
        boolean isDuplicated = domainDuplicateService.isDuplicateDomain(domainDTO);
        logger.info(this.getClass().getName() + "::STD DUPLICATE CONTROLLER::END");
        // ResponseEntity 객체를 생성하여 불린 값을 반환
        return ResponseEntity.ok(isDuplicated);
    }

    @PostMapping("/single-domainGroup/duplicate")
    public ResponseEntity<Boolean> domainDuplicate(@RequestBody DomainGroupDTO domainGroupDTO) {
        logger.info(this.getClass().getName() + "::STD DUPLICATE CONTROLLER::START");
        logger.info(this.getClass().getName() +"::STD DUPLICATE CONTROLLER" +  domainGroupDTO);
        boolean isDuplicated = domainDuplicateService.isDuplicateDomainGroup(domainGroupDTO);
        logger.info(this.getClass().getName() + "::STD DUPLICATE CONTROLLER::END");
        // ResponseEntity 객체를 생성하여 불린 값을 반환
        return ResponseEntity.ok(isDuplicated);
    }
}