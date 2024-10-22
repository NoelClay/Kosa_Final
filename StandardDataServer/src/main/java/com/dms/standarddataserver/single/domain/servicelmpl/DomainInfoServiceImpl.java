package com.dms.standarddataserver.single.domain.servicelmpl;
import com.dms.standarddataserver.single.domain.dto.DomainDTO;
import com.dms.standarddataserver.single.domain.mapper.DomainMapper;
import com.dms.standarddataserver.single.domain.service.DomainInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class DomainInfoServiceImpl implements DomainInfoService {

    private final DomainMapper domainMapper;

    @Autowired
    public DomainInfoServiceImpl(DomainMapper domainMapper){
        this.domainMapper = domainMapper;
    }

    public DomainDTO getDomainInfo(String domId) {
        log.info("getDomainInfo()::START");
        log.info("getDomainInfo()::domId= " + domId);

        DomainDTO domainDTO = domainMapper.getDomainInfo(domId);
        log.info("getDomainInfo()::domainDTO= " + domainDTO);

        log.info("getDomainInfo()::END");
        return domainDTO;
    }

}
