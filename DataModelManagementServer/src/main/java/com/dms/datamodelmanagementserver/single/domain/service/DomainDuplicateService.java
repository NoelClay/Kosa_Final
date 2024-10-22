package com.dms.datamodelmanagementserver.single.domain.service;

import com.dms.datamodelmanagementserver.single.domain.dto.DomainDTO;
import com.dms.datamodelmanagementserver.single.domain.dto.DomainGroupDTO;

public interface  DomainDuplicateService {
	
    public boolean isDuplicateDomainRest(DomainDTO domainDTO);

    // 도메인 그룹 등록 기능 구현 시 필요
    public boolean isDuplicateDomainGroupRest(DomainGroupDTO domainGroupDTO);
}
