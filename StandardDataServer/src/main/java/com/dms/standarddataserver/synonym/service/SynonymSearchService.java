package com.dms.standarddataserver.synonym.service;

import com.dms.standarddataserver.synonym.dto.SynonymDTO;

import java.util.List;


public interface  SynonymSearchService {
    List<SynonymDTO> selectList(SynonymDTO synonymDTO);
}
