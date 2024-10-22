package com.dms.standarddataserver.single.domain.controller;


import com.dms.standarddataserver.single.domain.dto.DomainDTO;
import com.dms.standarddataserver.single.domain.service.DomainSearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/std")
@Slf4j

public class DomainSearchController {

    private final DomainSearchService domainSearchService;

    @Autowired
    public DomainSearchController(DomainSearchService domainSearchService) {
        this.domainSearchService = domainSearchService;
    }




    @PostMapping("/single-domain/searchDomain")
    public ResponseEntity<?> getSingleDomainList(@RequestBody Map<String, String> requestMap) {

        String stdAreaId = requestMap.get("stdAreaId");
        String domainName = requestMap.get("domainName");
        String keyDomName = requestMap.get("keyDomName");

        log.info("STD::TermSearchController stdAreaId= " + stdAreaId);
        log.info("STD::TermSearchController domainName= " + domainName);

        List<DomainDTO> domainList = domainSearchService.selectDomainList(stdAreaId, domainName, keyDomName);

        return ResponseEntity.status(HttpStatus.OK).body(domainList);



    }

}
