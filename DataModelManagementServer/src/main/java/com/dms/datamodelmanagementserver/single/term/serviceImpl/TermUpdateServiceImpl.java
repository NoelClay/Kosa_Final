package com.dms.datamodelmanagementserver.single.term.serviceImpl;

import com.dms.datamodelmanagementserver.single.term.mapper.TermMapper;
import com.dms.datamodelmanagementserver.single.term.service.TermUpdateService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TermUpdateServiceImpl implements TermUpdateService {
	
	private final TermMapper termMapper;

    @Autowired
    public TermUpdateServiceImpl(TermMapper termMapper) {
        this.termMapper = termMapper;
    }
    
	@Override
	public boolean updateTermRest(String dicId, String domId, String dicDesc) {
		return termMapper.updateSingleTerm(dicId, domId, dicDesc);
	}
}
