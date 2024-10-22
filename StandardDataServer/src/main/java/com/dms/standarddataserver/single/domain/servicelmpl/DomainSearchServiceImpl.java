package com.dms.standarddataserver.single.domain.servicelmpl;


import com.dms.standarddataserver.single.domain.dto.DomainDTO;
import com.dms.standarddataserver.single.domain.mapper.DomainMapper;
import com.dms.standarddataserver.single.domain.service.DomainSearchService;
import com.dms.standarddataserver.standardArea.service.StandardAreaSelectOneService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class DomainSearchServiceImpl implements DomainSearchService {
    
    private final DomainMapper domainMapper;
    private final StandardAreaSelectOneService standardAreaSelectOneService;

    @Autowired
    public DomainSearchServiceImpl(DomainMapper domainMapper, StandardAreaSelectOneService standardAreaSelectOneService) {
        this.domainMapper = domainMapper;
        this.standardAreaSelectOneService = standardAreaSelectOneService;
    }

    public List<DomainDTO> selectDomainList(String stdAreaName, String domainName, String keyDomName) {
        log.info("STD::DOMAIN SEARCH = " + stdAreaName + ", " + domainName);
        String stdAreaId = standardAreaSelectOneService.selectOne(stdAreaName).getStdAreaId();
        log.info("STD::DOMAIN SEARCH stdId= " + stdAreaId);

        List<DomainDTO> result;

        if (domainName.isEmpty()) {
            // 빈 도메인명일 경우 모든 도메인 정보를 가져옴
            result = domainMapper.selectAllDomains(stdAreaId, keyDomName);
            log.info("STD::DOMAIN SEARCH ALL DOMAINS= " + result);
        } else {
            result = domainMapper.selectDomainList(stdAreaId, domainName, keyDomName);

            if (!result.isEmpty()) {
                log.info("STD::DOMAIN SEARCH LIST= " + result);
            }
        }

        return result;
    }


    
}
