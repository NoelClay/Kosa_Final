package com.dms.datamodelmanagementserver.collection.controller;

import com.dms.datamodelmanagementserver.collection.service.CollectionDownloadService;
import com.dms.datamodelmanagementserver.global.LogDefault;
import com.dms.datamodelmanagementserver.standardData.dto.StandardDataDTO;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/dms")
public class CollectionDownloadController {

    private final CollectionDownloadService collectionDownloadService;
    private final LogDefault logDefault;

    public CollectionDownloadController(CollectionDownloadService collectionDownloadService, LogDefault logDefault) {
        this.collectionDownloadService = collectionDownloadService;
        this.logDefault = logDefault;
    }


    @PostMapping("/collection/download")
    public ResponseEntity<String> downloadBulkDomain(@RequestBody List<StandardDataDTO> standardDataDTOList, HttpServletResponse response) throws IOException {
        logDefault.logCurrentMethod();
        return ResponseEntity.ok(collectionDownloadService.saveCollectionDataToExcel(standardDataDTOList, response));
    }
}
