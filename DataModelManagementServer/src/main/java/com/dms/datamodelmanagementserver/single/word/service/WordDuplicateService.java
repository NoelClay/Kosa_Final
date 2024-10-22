package com.dms.datamodelmanagementserver.single.word.service;

import com.dms.datamodelmanagementserver.single.word.dto.WordDTO;

public interface WordDuplicateService {
    boolean isDuplicateRest(WordDTO wordDTO);

}