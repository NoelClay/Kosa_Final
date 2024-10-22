package com.dms.datamodelmanagementserver.standardArea.service;

import com.dms.datamodelmanagementserver.standardArea.dto.StandardAreaDTO;

import jakarta.servlet.http.HttpServletRequest;

public interface StandardAreaInsertService {
     boolean insert(StandardAreaDTO standardAreaDTO, HttpServletRequest request);
}
