package com.dms.standarddataserver.word.serviceImpl;


import com.dms.standarddataserver.word.dto.WordDTO;
import com.dms.standarddataserver.word.mapper.WordMapper;
import com.dms.standarddataserver.word.service.WordAndTermDeleteService;
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
    public boolean deleteWordAndTerm(String deleteDicId) {

        boolean isTermUpdated = wordMapper.deleteWordAndTerm(deleteDicId);

        log.info("STD DELETE WORD AND TERM SERVICEIMPL = deleteDicId" + deleteDicId);

        return isTermUpdated;

    }

}
