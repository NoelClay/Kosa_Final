package com.dms.datamodelmanagementserver.bulk.word.service;

import com.dms.datamodelmanagementserver.bulk.domain.dto.DomainExcelDataDTO;
import com.dms.datamodelmanagementserver.bulk.word.dto.WordExcelDataDTO;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public interface BulkWordDownloadService {

    public String saveWordDataToExcel(List<WordExcelDataDTO> wordExcelDataDTOList, HttpServletResponse response) throws IOException;
}
