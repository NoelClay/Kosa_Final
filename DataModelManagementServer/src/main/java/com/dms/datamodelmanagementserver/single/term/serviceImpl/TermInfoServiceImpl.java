package com.dms.datamodelmanagementserver.single.term.serviceImpl;

import com.dms.datamodelmanagementserver.single.term.dto.TermDomainDTO;
import com.dms.datamodelmanagementserver.single.term.mapper.TermMapper;
import com.dms.datamodelmanagementserver.single.term.service.TermInfoService;

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
	
	@Override
	public TermDomainDTO getTermInfoRest(String dicId) {
        log.info("STD::getTermInfoServiceImpl dicId= " + dicId);
        return termMapper.getTermInfo(dicId);
	}
	
}
