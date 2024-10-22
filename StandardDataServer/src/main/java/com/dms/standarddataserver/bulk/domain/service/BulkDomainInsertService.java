package com.dms.standarddataserver.bulk.domain.service;

import com.dms.standarddataserver.bulk.domain.dto.DomainExcelDataDTO;

import java.util.List;

public interface BulkDomainInsertService {
    public List<DomainExcelDataDTO> insertBulkDomain(List<DomainExcelDataDTO> domainExcelDataDTOList);
}
