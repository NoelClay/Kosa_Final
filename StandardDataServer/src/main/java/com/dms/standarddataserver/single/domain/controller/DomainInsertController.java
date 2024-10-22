package com.dms.standarddataserver.single.domain.controller;

import com.dms.standarddataserver.global.LogDefault;
import com.dms.standarddataserver.single.domain.dto.DomainDTO;
import com.dms.standarddataserver.single.domain.service.DomainDuplicateService;
import com.dms.standarddataserver.single.domain.service.DomainInsertService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/std")
public class DomainInsertController {

    private static final Logger logger = LoggerFactory.getLogger(DomainInsertController.class);

    private final DomainInsertService domainInsertService;

    private final DomainDuplicateService domainDuplicateService;

    private final LogDefault logDefault;

    @Autowired
    public DomainInsertController(DomainDuplicateService domainDuplicateService, DomainInsertService domainInsertService, LogDefault logDefault) {
        this.domainDuplicateService = domainDuplicateService;
        this.domainInsertService = domainInsertService;
        this.logDefault = logDefault;
    }

    @PostMapping("/single-domain/insert")
    public ResponseEntity<Boolean> domainInsert(@RequestBody DomainDTO domainDTO) {
        logDefault.logCurrentMethod();
        logger.info("Received a POST request to /std/domain/insert");
        logger.info("Received DomainDTO: " + domainDTO);
        boolean isDomainInserted = domainInsertService.insertDomain(domainDTO);
        return ResponseEntity.status(HttpStatus.OK).body(isDomainInserted);
    }
}