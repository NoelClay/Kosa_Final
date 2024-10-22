package com.dms.datamodelmanagementserver.single.domain.serviceimpl;

import com.dms.datamodelmanagementserver.single.domain.dto.DomainDTO;
import com.dms.datamodelmanagementserver.single.domain.mapper.DomainMapper;
import com.dms.datamodelmanagementserver.single.domain.service.DomainInsertService;
import com.dms.datamodelmanagementserver.standardArea.dto.StandardAreaDTO;
import com.dms.datamodelmanagementserver.standardArea.service.StandardAreaSelectOneService;

import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public class DomainInsertServiceImpl implements DomainInsertService {
	
	private final DomainMapper domainMapper;
	private final StandardAreaSelectOneService standardAreaSelectOneService;
	

    public DomainInsertServiceImpl(DomainMapper domainMapper,
			StandardAreaSelectOneService standardAreaSelectOneService) {
		this.domainMapper = domainMapper;
		this.standardAreaSelectOneService = standardAreaSelectOneService;
	}


	@Override
    public boolean singleDomainInsertRest(DomainDTO domainDTO) {

        domainDTO.setDomId(UUID.randomUUID().toString());
        
    	String stdAreaId = domainDTO.getSelectStandardArea();
        if (stdAreaId != null) {
            StandardAreaDTO stdAreaIdValue = standardAreaSelectOneService.selectOne(stdAreaId);
            domainDTO.setSelectStandardArea(stdAreaIdValue.getStdAreaId());
            return domainMapper.insertDomain(domainDTO);
        }
        return false;
    }

}