package com.dms.datamodelmanagementserver.single.domain.service;

import com.dms.datamodelmanagementserver.single.domain.dto.DomainDTO;

import java.util.List;

public interface DomainSearchService {

    public List<DomainDTO> searchDomainNameByDomainNameRest(String stdAreaName, String domainName, String keyDomName);

}
