package com.dms.datamodelmanagementserver.single.word.serviceImpl;

import com.dms.datamodelmanagementserver.single.word.mapper.WordMapper;
import com.dms.datamodelmanagementserver.single.word.service.WordAndTermDeleteService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class WordAndTermDeleteServiceImpl implements WordAndTermDeleteService {
	
	private final WordMapper wordMapper;

    @Autowired
    public WordAndTermDeleteServiceImpl(WordMapper wordMapper) {
        this.wordMapper = wordMapper;
    }
	
	@Override
	public boolean deleteWordAndTermRest(String deleteDicId) {
		boolean isTermUpdated = wordMapper.deleteWordAndTerm(deleteDicId);
        log.info("STD DELETE WORD AND TERM SERVICEIMPL = deleteDicId" + deleteDicId);
        return isTermUpdated;
	}

}
