package com.dms.standarddataserver.bulk.term.service;

import com.dms.standarddataserver.bulk.term.dto.BulkTermDataDTO;

import java.util.List;

public interface BulkTermInsertService {
    public List<BulkTermDataDTO> insertBulkTerm(List<BulkTermDataDTO> bulkTermDataDTOList);
}
