package com.dms.standarddataserver.single.domain.controller;

import com.dms.standarddataserver.global.LogDefault;
import com.dms.standarddataserver.single.domain.dto.DomainDTO;
import com.dms.standarddataserver.single.domain.service.DomainUpdateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/std")
public class DomainUpdateController {

    private static final Logger logger = LoggerFactory.getLogger(DomainInsertController.class);

    private final DomainUpdateService domainUpdateService;

    private final LogDefault logDefault;

    @Autowired
    public DomainUpdateController(DomainUpdateService domainUpdateService, LogDefault logDefault) {
        this.domainUpdateService = domainUpdateService;
        this.logDefault = logDefault;
    }

    @PostMapping("/single-domain/update")
    public ResponseEntity<?> updateDomain(@RequestBody DomainDTO domainDTO) {
        logDefault.logCurrentMethod();
        int result = domainUpdateService.updateDomain(domainDTO);
        if (result == 1) {
            return ResponseEntity.status(HttpStatus.OK).body(domainDTO);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(false);
        }
    }


}
