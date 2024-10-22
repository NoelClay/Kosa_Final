package com.dms.datamodelmanagementserver.single.domain.serviceimpl;

import com.dms.datamodelmanagementserver.single.domain.dto.DomainDTO;
import com.dms.datamodelmanagementserver.single.domain.dto.DomainGroupDTO;
import com.dms.datamodelmanagementserver.single.domain.mapper.DomainMapper;
import com.dms.datamodelmanagementserver.single.domain.service.DomainDuplicateService;
import com.dms.datamodelmanagementserver.standardArea.service.StandardAreaSelectOneService;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class DomainDuplicateServiceImpl implements DomainDuplicateService {
	
	private final DomainMapper domainMapper;
    private final StandardAreaSelectOneService standardAreaSelectOneService;

    public DomainDuplicateServiceImpl(DomainMapper domainMapper,
			StandardAreaSelectOneService standardAreaSelectOneService) {
		super();
		this.domainMapper = domainMapper;
		this.standardAreaSelectOneService = standardAreaSelectOneService;
	}

	@Override
    public boolean isDuplicateDomainRest(DomainDTO domainDTO) {
		String stdAreaId = standardAreaSelectOneService.selectOne(domainDTO.getSelectStandardArea()).getStdAreaId();
		domainDTO.setSelectStandardArea(stdAreaId);
    	if (!StringUtils.hasText(domainDTO.getDomGroupId())) {
    		return domainMapper.isDuplicateDomain(domainDTO);
    	}
        return domainMapper.isDuplicateDomainInSameDomGroup(domainDTO);
    }

	// 도메인 그룹 등록 기능 구현 시 필요
    @Override
    public boolean isDuplicateDomainGroupRest(DomainGroupDTO domainGroupDTO) {
    	return domainMapper.isDuplicateDomainGroup(domainGroupDTO);
    }

}
