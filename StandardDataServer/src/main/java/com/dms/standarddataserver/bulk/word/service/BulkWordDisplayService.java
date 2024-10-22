package com.dms.standarddataserver.bulk.word.service;

import com.dms.standarddataserver.bulk.word.dto.WordExcelDataDTO;

import java.util.List;

public interface BulkWordDisplayService {

    public List<WordExcelDataDTO> validateBulkWord(List<WordExcelDataDTO> wordExcelDataDTOList);
}
