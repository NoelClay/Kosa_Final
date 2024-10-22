package com.dms.standarddataserver.bulk.term.service;

import com.dms.standarddataserver.bulk.term.dto.BulkTermExcelDataDTO;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface BulkTermDisplayService {

    //public List<BulkTermExcelDataDTO> validateBulkTerm(List<BulkTermExcelDataDTO> bulkTermDataDTOList);
    
    public List<BulkTermExcelDataDTO> validateBulkTerm(MultipartFile file, String stdAreaName);

}
