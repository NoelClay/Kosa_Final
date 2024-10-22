package com.dms.standarddataserver.standardArea.serviceImpl;

import com.dms.standarddataserver.standardArea.mapper.StandardAreaCheckDuplicateMapper;
import com.dms.standarddataserver.standardArea.service.StandardAreaCheckDuplicateService;
import org.springframework.stereotype.Service;

@Service
public class StandardAreaCheckDuplicateServiceImpl implements StandardAreaCheckDuplicateService {
    private final StandardAreaCheckDuplicateMapper standardAreaCheckDuplicateMapper;


    public StandardAreaCheckDuplicateServiceImpl(StandardAreaCheckDuplicateMapper standardAreaCheckDuplicateMapper) {
        this.standardAreaCheckDuplicateMapper = standardAreaCheckDuplicateMapper;
    }

    @Override
    public Boolean checkIfDuplicate(String standardAreaName) {
        return standardAreaCheckDuplicateMapper.checkIfDuplicate(standardAreaName);
    }
}