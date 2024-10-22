package com.dms.standarddataserver.word.serviceImpl;

import com.dms.standarddataserver.standardArea.service.StandardAreaSelectOneService;
import com.dms.standarddataserver.word.dto.WordDTO;
import com.dms.standarddataserver.word.mapper.WordMapper;
import com.dms.standarddataserver.word.service.WordInfoService;
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
    public WordDTO getWordAndTermInfo(String dicId) {
        WordDTO wordDTO;
        wordDTO = wordMapper.getWordAndTermInfo(dicId);

        return wordDTO;

    }
}
