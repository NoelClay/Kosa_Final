package com.dms.standarddataserver.single.domain.servicelmpl;

import com.dms.standarddataserver.single.domain.dto.DomainDTO;
import com.dms.standarddataserver.single.domain.mapper.DomainMapper;
import com.dms.standarddataserver.single.domain.service.DomainDeleteService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class DomainDeleteServiceImpl implements DomainDeleteService {

    private final DomainMapper domainMapper;
    @Autowired
    public DomainDeleteServiceImpl(DomainMapper domainMapper) {
        this.domainMapper = domainMapper;
    }

    @Override
    public boolean deleteDomain(DomainDTO domainDTO) {

        return domainMapper.deleteDomain(domainDTO);
    }


}

