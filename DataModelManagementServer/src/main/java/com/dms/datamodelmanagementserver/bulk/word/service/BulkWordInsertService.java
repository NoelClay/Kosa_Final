package com.dms.datamodelmanagementserver.bulk.word.service;

import com.dms.datamodelmanagementserver.bulk.word.dto.BulkWordDataDTO;

import java.util.List;

public interface BulkWordInsertService {
    public List<BulkWordDataDTO> insertBulkWord(List<BulkWordDataDTO> bulkWordDataDTOList);
}
