package com.dms.datamodelmanagementserver.single.word.serviceImpl;

import com.dms.datamodelmanagementserver.single.word.dto.WordDTO;
import com.dms.datamodelmanagementserver.single.word.mapper.WordMapper;
import com.dms.datamodelmanagementserver.single.word.service.WordUpdateService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class WordUpdateServiceImpl implements WordUpdateService {
	
	private final WordMapper wordMapper;
	
	@Autowired
    public WordUpdateServiceImpl(WordMapper wordMapper) {
        this.wordMapper = wordMapper;
    }
	
	@Override
	public boolean singleWordUpdateRest(WordDTO wordDTO) {
        boolean isWordUpdated = wordMapper.updateWord(wordDTO);
        return isWordUpdated;
	}

}
