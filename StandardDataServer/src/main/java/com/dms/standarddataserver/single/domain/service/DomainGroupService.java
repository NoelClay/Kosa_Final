package com.dms.standarddataserver.single.domain.service;


import com.dms.standarddataserver.single.domain.dto.DomainGroupDTO;

import java.util.List;

public interface DomainGroupService {
    public List<DomainGroupDTO> getDomainGroup(String subjAreaNameCookieValue);


}
