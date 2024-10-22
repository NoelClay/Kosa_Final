package com.dms.standarddataserver.single.domain.controller;
import com.dms.standarddataserver.global.LogDefault;
import com.dms.standarddataserver.single.domain.dto.DomainDTO;
import com.dms.standarddataserver.single.domain.service.DomainInfoService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.Map;

@RestController
@RequestMapping("/std")
@Slf4j
public class DomainInfoController {

    private final Logger logger = LoggerFactory.getLogger(DomainInfoController.class);

    private final DomainInfoService domainInfoService;

    private final LogDefault logDefault;


    @Autowired
    public DomainInfoController(DomainInfoService domainInfoService, LogDefault logDefault) {
        this.domainInfoService = domainInfoService;
        this.logDefault = logDefault;
    }

    @PostMapping("/single-domain/select")
    public ResponseEntity<?> getDomainInfo(@RequestBody Map<String, String> requestMap) {
        logDefault.logCurrentMethod();


        String domId = requestMap.get("domId");


        DomainDTO domainDTO = domainInfoService.getDomainInfo(domId);



        return ResponseEntity.status(HttpStatus.OK).body(domainDTO);
    }

}
