package com.dms.standarddataserver.word.service;

import com.dms.standarddataserver.word.dto.WordDTO;

public interface WordInfoService {

    WordDTO getWordAndTermInfo(String dicId);


}
