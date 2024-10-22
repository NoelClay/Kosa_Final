package com.dms.standarddataserver.term.serviceImpl;

import com.dms.standarddataserver.term.mapper.TermMapper;
import com.dms.standarddataserver.term.service.TermUpdateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TermUpdateServiceImpl implements TermUpdateService {

    private final TermMapper termMapper;

    @Autowired
    public TermUpdateServiceImpl(TermMapper termMapper) {
        this.termMapper = termMapper;
    }

    @Override
    public boolean updateSingleTerm(String dicId, String domId, String dicDesc) {

        boolean isTermUpdated = termMapper.updateSingleTerm(dicId, domId, dicDesc);

        log.info("STD UPDATE TERMSERVICEIMPL = dicId" + dicId);
        log.info("STD UPDATE TERMSERVICEIMPL = domId" + domId);

        return isTermUpdated;
    }
}
