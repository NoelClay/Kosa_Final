package com.dms.standarddataserver.standardArea.serviceImpl;

import com.dms.standarddataserver.standardArea.dto.StandardAreaDTO;
import com.dms.standarddataserver.standardArea.mapper.StandardAreaUpdateMapper;
import com.dms.standarddataserver.standardArea.service.StandardAreaCheckDuplicateService;
import com.dms.standarddataserver.standardArea.service.StandardAreaUpdateService;
import org.springframework.stereotype.Service;

@Service
public class StandardAreaUpdateServiceImpl implements StandardAreaUpdateService {
    private final StandardAreaUpdateMapper standardAreaUpdateMapper;
    private final StandardAreaCheckDuplicateService standardAreaCheckDuplicateService;

    public StandardAreaUpdateServiceImpl(StandardAreaUpdateMapper standardAreaUpdateMapper, StandardAreaCheckDuplicateService standardAreaCheckDuplicateService) {
        this.standardAreaUpdateMapper = standardAreaUpdateMapper;
        this.standardAreaCheckDuplicateService = standardAreaCheckDuplicateService;
    }

    @Override
    public void update(StandardAreaDTO standardAreaDTO) {
        standardAreaUpdateMapper.update(standardAreaDTO);
    }
}
