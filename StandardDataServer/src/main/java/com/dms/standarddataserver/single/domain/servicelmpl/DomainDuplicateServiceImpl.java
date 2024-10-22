package com.dms.standarddataserver.single.domain.servicelmpl;

import com.dms.standarddataserver.single.domain.dto.DomainDTO;
import com.dms.standarddataserver.single.domain.dto.DomainGroupDTO;
import com.dms.standarddataserver.single.domain.mapper.DomainMapper;
import com.dms.standarddataserver.single.domain.service.DomainDuplicateService;
import com.dms.standarddataserver.single.domain.service.DomainInsertService;
import com.dms.standarddataserver.standardArea.service.StandardAreaSelectOneService;

import org.springframework.beans.factory.annotation.Autowired;
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
    public boolean isDuplicateDomain(DomainDTO domainDTO) {
		String stdAreaId = standardAreaSelectOneService.selectOne(domainDTO.getSelectStandardArea()).getStdAreaId();
		domainDTO.setSelectStandardArea(stdAreaId);
    	if (!StringUtils.hasText(domainDTO.getDomGroupId())) {
    		return domainMapper.isDuplicateDomain(domainDTO);
    	}
        return domainMapper.isDuplicateDomainInSameDomGroup(domainDTO);
    }

    @Override
    public boolean isDuplicateDomainGroup(DomainGroupDTO domainGroupDTO) {
        return domainMapper.isDuplicateDomainGroup(domainGroupDTO);
    }


}


