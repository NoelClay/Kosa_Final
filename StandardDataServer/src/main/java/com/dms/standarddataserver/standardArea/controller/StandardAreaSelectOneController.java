package com.dms.standarddataserver.standardArea.controller;

import com.dms.standarddataserver.global.LogDefault;
import com.dms.standarddataserver.standardArea.dto.StandardAreaDTO;
import com.dms.standarddataserver.standardArea.service.StandardAreaSelectOneService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/std/standardArea")
public class StandardAreaSelectOneController {
    private final LogDefault logDefault;
    private final StandardAreaSelectOneService standardAreaSelectOneService;

    public StandardAreaSelectOneController(LogDefault logDefault, StandardAreaSelectOneService standardAreaSelectOneService) {
        this.logDefault = logDefault;
        this.standardAreaSelectOneService = standardAreaSelectOneService;
    }

    @PostMapping("/selectOne")
    public ResponseEntity<StandardAreaDTO> selectOne(@RequestParam("standardAreaName") String standardAreaName) {
        logDefault.logCurrentMethod();
        return ResponseEntity.status(HttpStatus.OK).body(standardAreaSelectOneService.selectOne(standardAreaName));
    }
}
