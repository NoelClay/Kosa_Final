package com.dms.standarddataserver.term.service;

import com.dms.standarddataserver.term.dto.TermDTO;

import java.util.List;

public interface TermInsertService {

    void singleTermInsert(List<TermDTO> termDTOList);

}
