package com.dms.datamodelmanagementserver.bulk.domain.service;

import com.dms.datamodelmanagementserver.bulk.domain.dto.DomainExcelDataDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BulkDomainDisplayService {

    public List<DomainExcelDataDTO> readDomainExcelData(MultipartFile file, String stdAreaName);
}
