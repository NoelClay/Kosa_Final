package com.dms.datamodelmanagementserver.single.word.serviceImpl;

import com.dms.datamodelmanagementserver.single.word.dto.WordDTO;
import com.dms.datamodelmanagementserver.single.word.mapper.WordMapper;
import com.dms.datamodelmanagementserver.single.word.service.WordService;
import com.dms.datamodelmanagementserver.standardArea.dto.StandardAreaDTO;
import com.dms.datamodelmanagementserver.standardArea.service.StandardAreaSelectOneService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
@Service
public class WordInsertServiceImpl implements WordService {
	
	private static final Logger logger = LoggerFactory.getLogger(WordInsertServiceImpl.class);

    private final WordMapper wordMapper;
    private final StandardAreaSelectOneService standardAreaSelectOneService;

    @Autowired
    public WordInsertServiceImpl(WordMapper wordMapper, StandardAreaSelectOneService standardAreaSelectOneService) {
        this.wordMapper = wordMapper;
        this.standardAreaSelectOneService = standardAreaSelectOneService;
    }
	
	@Override
	public boolean singleWordInsertRest(WordDTO wordDTO) {
		log.info("STD - WordInsertServiceImpl = " + wordDTO.getStdAreaId());
        String stdAreaId = wordDTO.getStdAreaId();

        if (stdAreaId != null) {
            StandardAreaDTO stdAreaIdValue = standardAreaSelectOneService.selectOne(stdAreaId);
            wordDTO.setStdAreaId(stdAreaIdValue.getStdAreaId());
            return wordMapper.insertWord(wordDTO);

        }
        return false;
	}

}
