package com.dms.standarddataserver.term.serviceImpl;


import com.dms.standarddataserver.term.dto.TermDomainDTO;
import com.dms.standarddataserver.term.mapper.TermMapper;
import com.dms.standarddataserver.term.service.TermInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TermInfoServiceImpl implements TermInfoService {

    private final TermMapper termMapper;

    @Autowired
    public TermInfoServiceImpl(TermMapper termMapper) {
        this.termMapper = termMapper;
    }


    public TermDomainDTO getTermInfo(String dicId) {
        TermDomainDTO termDomainDTO;

        log.info("STD::getTermInfoServiceImpl dicId= " + dicId);


        termDomainDTO = termMapper.getTermInfo(dicId);

        return termDomainDTO;


    }


}
