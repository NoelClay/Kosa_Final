package com.dms.standarddataserver.standardArea.serviceImpl;

import com.dms.standarddataserver.standardArea.dto.StandardAreaDTO;
import com.dms.standarddataserver.standardArea.mapper.StandardAreaSelectOneMapper;
import com.dms.standarddataserver.standardArea.service.StandardAreaSelectOneService;
import org.springframework.stereotype.Service;

@Service
public class StandardAreaSelectOneServiceImpl implements StandardAreaSelectOneService {
    private final StandardAreaSelectOneMapper standardAreaSelectOneMapper;

    public StandardAreaSelectOneServiceImpl(StandardAreaSelectOneMapper standardAreaSelectOneMapper) {
        this.standardAreaSelectOneMapper = standardAreaSelectOneMapper;
    }

    @Override
    public StandardAreaDTO selectOne(String standardAreaName) {
        return standardAreaSelectOneMapper.selectOne(standardAreaName);
    }
}
