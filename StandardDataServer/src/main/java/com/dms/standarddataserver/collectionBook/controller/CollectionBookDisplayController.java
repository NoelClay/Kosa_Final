package com.dms.standarddataserver.collectionBook.controller;

import com.dms.standarddataserver.collectionBook.service.CollectionBookDisplayService;
import com.dms.standarddataserver.global.LogDefault;
import com.dms.standarddataserver.standardData.dto.StandardDataDTO;
import com.dms.standarddataserver.standardData.dto.StandardDataSearchDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/std/collectionBook")
@Slf4j
public class CollectionBookDisplayController {
    private final LogDefault logDefault;
    private final CollectionBookDisplayService collectionBookDisplayService;

    public CollectionBookDisplayController(LogDefault logDefault, CollectionBookDisplayService collectionBookDisplayService) {
        this.logDefault = logDefault;
        this.collectionBookDisplayService = collectionBookDisplayService;
    }

    @PostMapping(value = "/display")
    public ResponseEntity<List<StandardDataDTO>> searchCollection(@RequestBody StandardDataSearchDTO standardDataSearchDTO) {
        logDefault.logCurrentMethod();
        List<StandardDataDTO> result = collectionBookDisplayService.searchCollection(standardDataSearchDTO);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
