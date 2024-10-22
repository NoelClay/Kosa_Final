package com.dms.datamodelmanagementserver.single.domain.service;

import com.dms.datamodelmanagementserver.single.domain.dto.DomainDTO;
import com.dms.datamodelmanagementserver.single.domain.dto.DomainGroupDTO;

public interface DomainInsertService {
    boolean singleDomainInsertRest(DomainDTO domainDTO);

}
