package com.dms.datamodelmanagementserver.single.term.service;

import com.dms.datamodelmanagementserver.single.word.dto.WordDTO;

import java.util.List;

public interface TermSearchService {

    List<WordDTO> selectWordListRest(String stdAreaId, String searchWord);


}
