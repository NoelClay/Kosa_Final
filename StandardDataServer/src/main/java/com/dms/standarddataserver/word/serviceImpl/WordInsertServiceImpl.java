package com.dms.standarddataserver.word.serviceImpl;

import com.dms.standarddataserver.standardArea.dto.StandardAreaDTO;
import com.dms.standarddataserver.standardArea.service.StandardAreaSelectOneService;
import com.dms.standarddataserver.word.dto.WordDTO;
import com.dms.standarddataserver.word.mapper.WordMapper;
import com.dms.standarddataserver.word.service.WordService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public boolean insertWord(WordDTO wordDTO) {

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
