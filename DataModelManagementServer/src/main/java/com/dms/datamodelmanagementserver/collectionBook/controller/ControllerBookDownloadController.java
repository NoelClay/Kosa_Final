package com.dms.datamodelmanagementserver.collectionBook.controller;

import com.dms.datamodelmanagementserver.collectionBook.service.CollectionBookDownloadService;
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
@RequestMapping("/dms/collectionBook")
public class ControllerBookDownloadController {

    private final CollectionBookDownloadService collectionBookDownloadService;
    private final LogDefault logDefault;

    public ControllerBookDownloadController(CollectionBookDownloadService collectionBookDownloadService, LogDefault logDefault) {
        this.collectionBookDownloadService = collectionBookDownloadService;
        this.logDefault = logDefault;
    }

    @PostMapping("/download")
    public ResponseEntity<String> downloadBulkDomain(@RequestBody List<StandardDataDTO> standardDataDTOList, HttpServletResponse response) throws IOException {
        logDefault.logCurrentMethod();
        return ResponseEntity.ok(collectionBookDownloadService.saveCollectionBookDataToExcel(standardDataDTOList, response));
    }
}
