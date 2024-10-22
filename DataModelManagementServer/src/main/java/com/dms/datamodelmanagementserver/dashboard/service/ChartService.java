package com.dms.datamodelmanagementserver.dashboard.service;

import com.dms.datamodelmanagementserver.dashboard.dto.ChartDTO;

import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface ChartService {
    public List<ChartDTO> getChart(String standardAreaName, HttpServletRequest request);
}
