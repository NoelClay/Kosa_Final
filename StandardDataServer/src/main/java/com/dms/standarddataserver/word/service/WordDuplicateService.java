package com.dms.standarddataserver.word.service;

import com.dms.standarddataserver.word.dto.WordDTO;

public interface WordDuplicateService {

    public boolean isDuplicate(WordDTO wordDTO);


}
