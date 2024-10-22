package com.dms.datamodelmanagementserver.collectionBook.controller;

import com.dms.datamodelmanagementserver.collectionBook.service.CollectionBookDisplayService;
import com.dms.datamodelmanagementserver.global.LogDefault;
import com.dms.datamodelmanagementserver.standardData.dto.StandardDataDTO;
import com.dms.datamodelmanagementserver.standardData.dto.StandardDataSearchDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/dms/collectionBook")
public class CollectionBookDisplayController {

    private final CollectionBookDisplayService collectionBookDisplayService;
    private final LogDefault logDefault;

    public CollectionBookDisplayController(CollectionBookDisplayService collectionBookDisplayService, LogDefault logDefault) {
        this.collectionBookDisplayService = collectionBookDisplayService;
        this.logDefault = logDefault;
    }

    @PostMapping("/display")
    @ResponseBody
    public ResponseEntity<List<StandardDataDTO>> searchCollection(@RequestBody StandardDataSearchDTO standardDataSearchDTO) {
        logDefault.logCurrentMethod();
        List<StandardDataDTO> standardDataDTOList = collectionBookDisplayService.searchCollection(standardDataSearchDTO);
        return ResponseEntity.status(HttpStatus.OK).body(standardDataDTOList);
    }

}
