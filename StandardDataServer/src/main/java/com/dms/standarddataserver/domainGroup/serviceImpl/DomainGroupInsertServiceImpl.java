package com.dms.standarddataserver.domainGroup.serviceImpl;

import com.dms.standarddataserver.bulk.domain.dto.DomainExcelDataDTO;
import com.dms.standarddataserver.bulk.domain.mapper.BulkDomainMapper;
import com.dms.standarddataserver.domainGroup.dto.DomainGroupDTO;
import com.dms.standarddataserver.domainGroup.mapper.DomainGroupMapper;
import com.dms.standarddataserver.domainGroup.service.DomainGroupInsertService;
import com.dms.standarddataserver.standardArea.service.StandardAreaSelectOneService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DomainGroupInsertServiceImpl implements DomainGroupInsertService {

    private final DomainGroupMapper domainGroupMapper;
    private final StandardAreaSelectOneService standardAreaSelectOneService;

    public DomainGroupInsertServiceImpl(DomainGroupMapper domainGroupMapper,
			StandardAreaSelectOneService standardAreaSelectOneService) {
		this.domainGroupMapper = domainGroupMapper;
		this.standardAreaSelectOneService = standardAreaSelectOneService;
	}

	@Override
	public DomainGroupDTO insertDomainGroup(DomainGroupDTO domainGroupDto) {
		String subjAreaId = standardAreaSelectOneService.selectOne(domainGroupDto.getSubjAreaId()).getStdAreaId();
		domainGroupDto.setSubjAreaId(subjAreaId);
		domainGroupDto.setDomainGroupId(UUID.randomUUID().toString());
		int insertResult = domainGroupMapper.insertDomainGroup(domainGroupDto);
		return insertResult > 0 ? domainGroupDto : null;
	}

}
