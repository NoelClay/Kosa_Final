package com.dms.standarddataserver.bulk.word.service;

import com.dms.standarddataserver.bulk.domain.dto.DomainExcelDataDTO;
import com.dms.standarddataserver.bulk.word.dto.BulkWordDataDTO;

import java.util.List;

public interface BulkWordInsertService {
    public List<BulkWordDataDTO> insertBulkWord(List<BulkWordDataDTO> bulkWordDataDTOList);
}
