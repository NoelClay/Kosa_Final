package com.dms.standarddataserver.term.controller;

import com.dms.standarddataserver.term.service.TermUpdateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/std")
@Slf4j
public class TermUpdateController {

    private final TermUpdateService termUpdateService;

    @Autowired
    public TermUpdateController(TermUpdateService termUpdateService) {
        this.termUpdateService = termUpdateService;
    }


    @PostMapping("/single-term/update")
    public ResponseEntity<?> singleUpdateTerm(@RequestBody Map<String, String> requestMap) {


        String dicId = requestMap.get("dicId");
        String domId = requestMap.get("domId");
        String dicDesc = requestMap.get("dicDesc");



        boolean isTermUpdated = termUpdateService.updateSingleTerm(dicId, domId, dicDesc);

        return ResponseEntity.status(HttpStatus.OK).body(isTermUpdated);



    }

}
