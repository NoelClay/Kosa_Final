package com.dms.datamodelmanagementserver.single.word.serviceImpl;

import com.dms.datamodelmanagementserver.single.word.dto.WordDTO;
import com.dms.datamodelmanagementserver.single.word.mapper.WordMapper;
import com.dms.datamodelmanagementserver.single.word.service.WordDuplicateService;
import com.dms.datamodelmanagementserver.standardArea.dto.StandardAreaDTO;
import com.dms.datamodelmanagementserver.standardArea.service.StandardAreaSelectOneService;

import org.springframework.beans.factory.annotation.Autowired;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class WordDuplicateServiceImpl implements WordDuplicateService {
	
	private final WordMapper wordMapper;
    private final StandardAreaSelectOneService standardAreaSelectOneService;

    @Autowired
    public WordDuplicateServiceImpl(WordMapper wordMapper, StandardAreaSelectOneService standardAreaSelectOneService) {
        this.wordMapper = wordMapper;
        this.standardAreaSelectOneService = standardAreaSelectOneService;
    }

    @Override
    public boolean isDuplicateRest(WordDTO wordDTO) {
        log.info("::STD WordDuplicateServiceImpl " +  wordDTO.getStdAreaId());
//        String stdAreaId = wordDTO.getStdAreaId();
//
//        if (stdAreaId != null) {
//            StandardAreaDTO stdAreaIdValue = standardAreaSelectOneService.selectOne(stdAreaId);
//            wordDTO.setStdAreaId(stdAreaId);
//            log.info("::STD WordDuplicateServiceImpl stdAreaIdValue " +  stdAreaIdValue.getStdAreaId());
//        }

        log.info("::STD WordDuplicateServiceImpl " +  wordDTO);

        boolean isDuplicate = wordMapper.isDuplicate(wordDTO);

        log.info("::STD WordDuplicateServiceImpl End" +  isDuplicate);

        return isDuplicate;

    }

}
