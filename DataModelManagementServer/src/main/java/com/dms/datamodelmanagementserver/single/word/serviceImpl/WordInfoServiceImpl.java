package com.dms.datamodelmanagementserver.single.word.serviceImpl;

import com.dms.datamodelmanagementserver.single.word.dto.WordDTO;
import com.dms.datamodelmanagementserver.single.word.mapper.WordMapper;
import com.dms.datamodelmanagementserver.single.word.service.WordInfoService;
import com.dms.datamodelmanagementserver.standardArea.service.StandardAreaSelectOneService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class WordInfoServiceImpl implements WordInfoService {
	
	private final WordMapper wordMapper;
	
	@Autowired
    public WordInfoServiceImpl(WordMapper wordMapper, StandardAreaSelectOneService standardAreaSelectOneService) {
        this.wordMapper = wordMapper;
    }
	
	@Override
	public WordDTO getWordAndTermInfoRest(String dicId) {
		WordDTO wordDTO;
        wordDTO = wordMapper.getWordAndTermInfo(dicId);
        return wordDTO;
	}

}
