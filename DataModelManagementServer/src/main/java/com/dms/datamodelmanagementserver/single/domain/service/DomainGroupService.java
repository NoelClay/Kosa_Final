package com.dms.datamodelmanagementserver.single.domain.service;

import com.dms.datamodelmanagementserver.single.domain.dto.DomainGroupDTO;

import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface DomainGroupService {
    public List<DomainGroupDTO> getDomainGroup(HttpServletRequest request);
}
