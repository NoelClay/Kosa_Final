package com.dms.datamodelmanagementserver.single.domain.serviceimpl;

import com.dms.datamodelmanagementserver.single.domain.dto.DomainDTO;
import com.dms.datamodelmanagementserver.single.domain.mapper.DomainMapper;
import com.dms.datamodelmanagementserver.single.domain.service.DomainInfoService;

import org.springframework.stereotype.Service;

@Service
public class DomainInfoServiceImpl implements DomainInfoService {

	private final DomainMapper domainMapper;

    public DomainInfoServiceImpl(DomainMapper domainMapper) {
		this.domainMapper = domainMapper;
	}

	@Override
    public DomainDTO getDomainInfoRest(String domId) {
        return domainMapper.getDomainInfo(domId);
    }

}