package com.dms.standarddataserver.term.service;


import com.dms.standarddataserver.term.dto.TermDomainDTO;

public interface TermInfoService {

    TermDomainDTO getTermInfo(String dicId);


}
