package com.dms.datamodelmanagementserver.single.domain.serviceimpl;

import com.dms.datamodelmanagementserver.single.domain.dto.DomainDTO;
import com.dms.datamodelmanagementserver.single.domain.mapper.DomainMapper;
import com.dms.datamodelmanagementserver.single.domain.service.DomainDeleteService;

import org.springframework.stereotype.Service;

@Service
public class DomainDeleteServiceImpl implements DomainDeleteService {
	
	private final DomainMapper domainMapper;

	public DomainDeleteServiceImpl(DomainMapper domainMapper) {
		super();
		this.domainMapper = domainMapper;
	}

	@Override
    public boolean deleteDomain(DomainDTO domainDTO) {
		return domainMapper.deleteDomain(domainDTO);
    }

}
