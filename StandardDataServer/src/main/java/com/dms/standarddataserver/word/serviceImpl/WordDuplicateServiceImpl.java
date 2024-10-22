package com.dms.standarddataserver.word.serviceImpl;

import com.dms.standarddataserver.standardArea.dto.StandardAreaDTO;
import com.dms.standarddataserver.standardArea.service.StandardAreaSelectOneService;
import com.dms.standarddataserver.word.dto.WordDTO;
import com.dms.standarddataserver.word.mapper.WordMapper;
import com.dms.standarddataserver.word.service.WordDuplicateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    public boolean isDuplicate(WordDTO wordDTO) {
        log.info("::STD WordDuplicateServiceImpl " +  wordDTO.getStdAreaId());
        String stdAreaId = wordDTO.getStdAreaId();

        if (stdAreaId != null) {
            StandardAreaDTO stdAreaIdValue = standardAreaSelectOneService.selectOne(stdAreaId);
            wordDTO.setStdAreaId(stdAreaIdValue.getStdAreaId());
            log.info("::STD WordDuplicateServiceImpl stdAreaIdValue " +  stdAreaIdValue.getStdAreaId());
        }

        log.info("::STD WordDuplicateServiceImpl " +  wordDTO);

        boolean isDuplicate = wordMapper.isDuplicate(wordDTO);

        log.info("::STD WordDuplicateServiceImpl End" +  isDuplicate);



        return isDuplicate;


    }

}
