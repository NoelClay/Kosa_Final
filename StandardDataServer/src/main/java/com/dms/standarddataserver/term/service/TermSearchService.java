package com.dms.standarddataserver.term.service;

import com.dms.standarddataserver.word.dto.WordDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TermSearchService {
    List<WordDTO> selectWordList(String stdAreaId, String searchWord);
}