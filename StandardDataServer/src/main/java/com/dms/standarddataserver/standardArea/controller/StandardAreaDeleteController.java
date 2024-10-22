package com.dms.standarddataserver.standardArea.controller;

import com.dms.standarddataserver.global.LogDefault;
import com.dms.standarddataserver.standardArea.dto.StandardAreaDTO;
import com.dms.standarddataserver.standardArea.service.StandardAreaDeleteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "/std/standardArea")
public class StandardAreaDeleteController {
    private final LogDefault logDefault;
    private final StandardAreaDeleteService standardAreaDeleteService;


    public StandardAreaDeleteController(LogDefault logDefault, StandardAreaDeleteService standardAreaDeleteService) {
        this.logDefault = logDefault;
        this.standardAreaDeleteService = standardAreaDeleteService;
    }

    @PostMapping(value = "/delete")
    public ResponseEntity<Boolean> delete(@RequestBody StandardAreaDTO standardAreaDTO) {
        logDefault.logCurrentMethod();
        boolean result = standardAreaDeleteService.delete(standardAreaDTO);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
