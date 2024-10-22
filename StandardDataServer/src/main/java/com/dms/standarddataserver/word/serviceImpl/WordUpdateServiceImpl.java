package com.dms.standarddataserver.word.serviceImpl;


import com.dms.standarddataserver.word.dto.WordDTO;
import com.dms.standarddataserver.word.mapper.WordMapper;
import com.dms.standarddataserver.word.service.WordUpdateService;
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
    public boolean updateWord(WordDTO wordDTO) {

        boolean isWordUpdated = wordMapper.updateWord(wordDTO);

        return isWordUpdated;

    }

}
