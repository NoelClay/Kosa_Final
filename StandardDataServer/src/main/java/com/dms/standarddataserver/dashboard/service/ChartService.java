package com.dms.standarddataserver.dashboard.service;

import com.dms.standarddataserver.dashboard.dto.ChartDTO;

import java.util.List;

public interface ChartService {
    List<ChartDTO> getChart(String standardAreaName);
}
