package com.dms.datamodelmanagementserver.bulk.domain.service;

import com.dms.datamodelmanagementserver.bulk.domain.dto.DomainExcelDataDTO;

import java.util.List;

public interface BulkDomainInsertService {

    public List<DomainExcelDataDTO> insertBulkDomain(List<DomainExcelDataDTO> domainExcelDataDTOList);
}
