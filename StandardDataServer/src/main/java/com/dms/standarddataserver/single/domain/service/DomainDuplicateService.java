package com.dms.standarddataserver.single.domain.service;

import com.dms.standarddataserver.single.domain.dto.DomainDTO;
import com.dms.standarddataserver.single.domain.dto.DomainGroupDTO;

public interface DomainDuplicateService {

    public boolean isDuplicateDomain(DomainDTO domainDTO);

    public boolean isDuplicateDomainGroup(DomainGroupDTO domainGropupDTO);

}
