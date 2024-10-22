package com.dms.standarddataserver.domainGroup.mapper;

import com.dms.standarddataserver.domainGroup.dto.DomainGroupDTO;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DomainGroupMapper {
	public int insertDomainGroup(DomainGroupDTO domainGroupDto);

}
