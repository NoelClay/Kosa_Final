package com.dms.standarddataserver.bulk.domain.service;

import com.dms.standarddataserver.bulk.domain.dto.DomainExcelDataDTO;

import java.util.List;

public interface BulkDomainDisplayService {

    public List<DomainExcelDataDTO> validateBulkDomain(List<DomainExcelDataDTO> domainExcelDataDTOList);

}
