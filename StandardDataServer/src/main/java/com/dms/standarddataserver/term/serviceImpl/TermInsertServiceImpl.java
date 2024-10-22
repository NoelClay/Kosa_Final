package com.dms.standarddataserver.term.serviceImpl;

import com.dms.standarddataserver.term.dto.TermDTO;
import com.dms.standarddataserver.term.mapper.TermMapper;
import com.dms.standarddataserver.term.service.TermInsertService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class TermInsertServiceImpl implements TermInsertService {


    private final TermMapper termMapper;

    @Autowired
    public TermInsertServiceImpl(TermMapper termMapper) {
        this.termMapper = termMapper;
    }

    public void singleTermInsert(List<TermDTO> termDTOList) {


        for (TermDTO terDTOid : termDTOList) {
        	log.info("STDINSERTSERVICE:::termid= " + terDTOid.getSubjAreaId());
            log.info("STDINSERTSERVICE:::termid= " + terDTOid.getTermId());
            log.info("STDINSERTSERVICE:::orderNo= " + terDTOid.getOrderNo());
            log.info("STDINSERTSERVICE:::dicId= " + terDTOid.getDicId());
        }

        termMapper.singleTermInsert(termDTOList);
    }

}
