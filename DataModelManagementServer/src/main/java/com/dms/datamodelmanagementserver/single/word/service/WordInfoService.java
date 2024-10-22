package com.dms.datamodelmanagementserver.single.word.service;

import com.dms.datamodelmanagementserver.single.word.dto.WordDTO;

public interface WordInfoService {

    WordDTO getWordAndTermInfoRest(String dicId);


}
