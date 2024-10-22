package com.dms.standarddataserver.term.controller;


import com.dms.standarddataserver.term.dto.TermDomainDTO;
import com.dms.standarddataserver.term.service.TermInfoService;
import com.dms.standarddataserver.word.dto.WordDTO;
import lombok.extern.slf4j.Slf4j;
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
public class TermInfoController {

    private final TermInfoService termInfoService;

    @Autowired
    public TermInfoController(TermInfoService termInfoService) {
        this.termInfoService = termInfoService;
    }

    @PostMapping("/single-term/selectTerm")

    public ResponseEntity<?> getTermInfo(@RequestBody Map<String, String> requestMap) {

        String dicId = requestMap.get("dicId");

        log.info("STD::ControllergetTermInfo dicId= " + dicId);



        TermDomainDTO termDomainDTO = termInfoService.getTermInfo(dicId);

        log.info("STD::ControllergetTermInfo termDomainDTO= " + termDomainDTO);


        return ResponseEntity.status(HttpStatus.OK).body(termDomainDTO);




    }


}
