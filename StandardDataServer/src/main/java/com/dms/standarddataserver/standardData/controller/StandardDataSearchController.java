package com.dms.standarddataserver.standardData.controller;

import com.dms.standarddataserver.global.LogDefault;
import com.dms.standarddataserver.standardData.dto.StandardDataDTO;
import com.dms.standarddataserver.standardData.dto.StandardDataSearchDTO;
import com.dms.standarddataserver.standardData.service.StandardDataSearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/std/standardData")
@Slf4j
public class StandardDataSearchController {
    private final LogDefault logDefault;
    private final StandardDataSearchService standardDataSearchService;

    public StandardDataSearchController(LogDefault logDefault, StandardDataSearchService standardDataSearchService) {
        this.logDefault = logDefault;
        this.standardDataSearchService = standardDataSearchService;
    }

    @PostMapping(value = "/search")
    public ResponseEntity<List<StandardDataDTO>> search(@RequestBody StandardDataSearchDTO standardDataSearchDTO) {
        logDefault.logCurrentMethod();
        List<StandardDataDTO> result = standardDataSearchService.search(standardDataSearchDTO);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

}
