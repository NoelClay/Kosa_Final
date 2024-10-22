package com.dms.datamodelmanagementserver.single.domain.serviceimpl;

import com.dms.datamodelmanagementserver.single.domain.dto.DomainDTO;
import com.dms.datamodelmanagementserver.single.domain.mapper.DomainMapper;
import com.dms.datamodelmanagementserver.single.domain.service.DomainUpdateService;
import com.dms.datamodelmanagementserver.standardArea.service.StandardAreaSelectOneService;

import org.springframework.stereotype.Service;

@Service
public class DomainUpdateServiceImpl implements DomainUpdateService {
	
    private final DomainMapper domainMapper;
    private final StandardAreaSelectOneService standardAreaSelectOneService;

	public DomainUpdateServiceImpl(DomainMapper domainMapper,
			StandardAreaSelectOneService standardAreaSelectOneService) {
		this.domainMapper = domainMapper;
		this.standardAreaSelectOneService = standardAreaSelectOneService;
	}

	@Override
    public DomainDTO singleDomainUpdateRest(DomainDTO domainDTO) {
		
		String selectStandardArea = standardAreaSelectOneService.selectOne(domainDTO.getSelectStandardArea()).getStdAreaId();
        domainDTO.setSelectStandardArea(selectStandardArea);
        
        boolean isSuccess = domainMapper.updateDomain(domainDTO);
        if(isSuccess) {
        	return domainDTO;
        }
    	return null;
    }

}
