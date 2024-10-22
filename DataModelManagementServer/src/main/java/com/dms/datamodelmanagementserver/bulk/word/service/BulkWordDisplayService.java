package com.dms.datamodelmanagementserver.bulk.word.service;

import com.dms.datamodelmanagementserver.bulk.word.dto.WordExcelDataDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BulkWordDisplayService {

    public List<WordExcelDataDTO> readWordExcelData(MultipartFile file, String stdAreaName);
}
