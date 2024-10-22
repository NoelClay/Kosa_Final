package com.dms.datamodelmanagementserver.standardArea.service;

import com.dms.datamodelmanagementserver.standardArea.dto.StandardAreaDTO;

import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface StandardAreaSelectListService {
    public List<StandardAreaDTO> selectList(HttpServletRequest request);
    public List<StandardAreaDTO> selectList(String userId);
    
}
