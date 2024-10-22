package com.dms.datamodelmanagementserver.bulk.domain.service;

import com.dms.datamodelmanagementserver.bulk.domain.dto.DomainExcelDataDTO;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public interface BulkDomainDownloadService {

    public String saveDomainDataToExcel(List<DomainExcelDataDTO> domainExcelDataDTOList, HttpServletResponse response) throws IOException;
}
