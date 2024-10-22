package com.dms.datamodelmanagementserver.bulk.term.service;

import com.dms.datamodelmanagementserver.bulk.term.dto.BulkTermDataDTO;

import java.util.List;

public interface BulkTermInsertService {
    public List<BulkTermDataDTO> insertBulkTerm(List<BulkTermDataDTO> bulkTermDataDTOList);
}
