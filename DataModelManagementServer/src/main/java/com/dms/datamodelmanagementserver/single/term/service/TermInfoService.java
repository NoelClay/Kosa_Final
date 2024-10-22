package com.dms.datamodelmanagementserver.single.term.service;

import com.dms.datamodelmanagementserver.single.domain.dto.DomainDTO;
import com.dms.datamodelmanagementserver.single.term.dto.TermDomainDTO;
import com.dms.datamodelmanagementserver.single.word.dto.WordDTO;

public interface TermInfoService {


    TermDomainDTO getTermInfoRest(String dicId);



}
