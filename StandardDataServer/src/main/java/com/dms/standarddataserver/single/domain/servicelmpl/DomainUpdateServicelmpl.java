package com.dms.standarddataserver.single.domain.servicelmpl;


import com.dms.standarddataserver.single.domain.dto.DomainDTO;
import com.dms.standarddataserver.single.domain.mapper.DomainMapper;
//import com.dms.standarddataserver.single.domain.service.DomainUpdateService;
import com.dms.standarddataserver.single.domain.service.DomainUpdateService;
import com.dms.standarddataserver.standardArea.dto.StandardAreaDTO;
import com.dms.standarddataserver.standardArea.service.StandardAreaSelectOneService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class DomainUpdateServicelmpl implements DomainUpdateService {


    private final DomainMapper domainMapper;

    private final StandardAreaSelectOneService standardAreaSelectOneService;

    @Autowired
    public DomainUpdateServicelmpl(DomainMapper domainMapper, StandardAreaSelectOneService standardAreaSelectOneService) {
        this.domainMapper = domainMapper;
        this.standardAreaSelectOneService = standardAreaSelectOneService;
    }


    @Override
    public int updateDomain(DomainDTO domainDTO) {
        log.info("STD - DomainUpdateServiceImpl = " + domainDTO.getSelectStandardArea());
        String selectStandardArea = standardAreaSelectOneService.selectOne(domainDTO.getSelectStandardArea()).getStdAreaId();
        domainDTO.setSelectStandardArea(selectStandardArea);
        return domainMapper.updateDomain(domainDTO);

    }
}


