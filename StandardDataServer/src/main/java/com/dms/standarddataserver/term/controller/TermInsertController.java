package com.dms.standarddataserver.term.controller;

import com.dms.standarddataserver.term.dto.TermDTO;
import com.dms.standarddataserver.term.service.TermInsertService;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class TermInsertController {

    private final TermInsertService termInsertService;

    @Autowired
    public TermInsertController(TermInsertService termInsertService) {
        this.termInsertService = termInsertService;
    }

    @PostMapping("/single-term/insert")
    public ResponseEntity<?> singleTermInsert(@RequestBody List<TermDTO> termDTOList ){

        log.info("STD:::TERMINSERTCONTROLLER= " + termDTOList);


        termInsertService.singleTermInsert(termDTOList);

        boolean termInsertResult = !termDTOList.isEmpty();

        return ResponseEntity.status(HttpStatus.OK).body(termInsertResult);


    }



}
