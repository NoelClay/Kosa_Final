package com.dms.datamodelmanagementserver.bulk.term.service;

import com.dms.datamodelmanagementserver.bulk.term.dto.BulkTermExcelDataDTO;
import com.dms.datamodelmanagementserver.bulk.word.dto.WordExcelDataDTO;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public interface BulkTermDownloadService {

    public String saveTermDataToExcel(List<BulkTermExcelDataDTO> bulkTermExcelDataDTOList, HttpServletResponse response) throws IOException;
}
