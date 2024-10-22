package com.dms.datamodelmanagementserver.bulk.term.service;

import com.dms.datamodelmanagementserver.bulk.term.dto.BulkTermExcelDataDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BulkTermDisplayService {

    public List<BulkTermExcelDataDTO> readTermExcelData(MultipartFile file, String stdAreaName);
}
