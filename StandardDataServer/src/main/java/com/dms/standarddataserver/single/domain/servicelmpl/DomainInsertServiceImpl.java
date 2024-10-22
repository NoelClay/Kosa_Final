package com.dms.standarddataserver.single.domain.servicelmpl;

import com.dms.standarddataserver.global.LogDefault;
import com.dms.standarddataserver.single.domain.dto.DomainDTO;
import com.dms.standarddataserver.single.domain.mapper.DomainMapper;
import com.dms.standarddataserver.single.domain.service.DomainInsertService;
import com.dms.standarddataserver.standardArea.dto.StandardAreaDTO;
import com.dms.standarddataserver.standardArea.service.StandardAreaSelectOneService;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DomainInsertServiceImpl implements DomainInsertService {
    private static final Logger logger = LoggerFactory.getLogger(DomainInsertServiceImpl.class);

    private final DomainMapper domainMapper;
    private final StandardAreaSelectOneService standardAreaSelectOneService;
    private final LogDefault logDefault;

    @Autowired
    public DomainInsertServiceImpl(DomainMapper domainMapper, StandardAreaSelectOneService standardAreaSelectOneService, LogDefault logDefault, LogDefault logDefault1) {
        this.domainMapper = domainMapper;
        this.standardAreaSelectOneService = standardAreaSelectOneService;
        this.logDefault = logDefault1;
    }
    @Override
    public boolean insertDomain(DomainDTO domainDTO) {
        logDefault.logCurrentMethod();
        log.info("STD - DomainInsertServiceImpl = " + domainDTO.getSelectStandardArea());
        String stdAreaId = domainDTO.getSelectStandardArea();
        if (stdAreaId != null) {
            StandardAreaDTO stdAreaIdValue = standardAreaSelectOneService.selectOne(stdAreaId);
            domainDTO.setSelectStandardArea(stdAreaIdValue.getStdAreaId());
            return domainMapper.insertDomain(domainDTO);

        }
        return false;
    }
}