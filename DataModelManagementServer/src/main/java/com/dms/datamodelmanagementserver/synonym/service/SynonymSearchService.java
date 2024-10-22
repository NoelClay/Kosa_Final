package com.dms.datamodelmanagementserver.synonym.service;

import com.dms.datamodelmanagementserver.synonym.dto.SynonymDTO;

import java.util.List;

public interface SynonymSearchService {

    List<SynonymDTO> selectList(SynonymDTO synonymDTO);
}
