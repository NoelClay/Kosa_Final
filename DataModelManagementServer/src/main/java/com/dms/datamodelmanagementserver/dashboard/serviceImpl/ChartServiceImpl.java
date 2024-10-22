package com.dms.datamodelmanagementserver.dashboard.serviceImpl;

import com.dms.datamodelmanagementserver.dashboard.dto.ChartDTO;
import com.dms.datamodelmanagementserver.dashboard.mapper.ChartMapper;
import com.dms.datamodelmanagementserver.dashboard.service.ChartService;

import com.dms.datamodelmanagementserver.global.session.service.SessionService;
import com.dms.datamodelmanagementserver.standardArea.dto.StandardAreaDTO;
import com.dms.datamodelmanagementserver.standardArea.service.StandardAreaSelectOneService;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChartServiceImpl implements ChartService {
	
	private final SessionService sessionService;
	private final StandardAreaSelectOneService standardAreaSelectOneService;
    private final ChartMapper chartMapper;
    
    
	public ChartServiceImpl(SessionService sessionService, StandardAreaSelectOneService standardAreaSelectOneService,
			ChartMapper chartMapper) {
		this.sessionService = sessionService;
		this.standardAreaSelectOneService = standardAreaSelectOneService;
		this.chartMapper = chartMapper;
	}

	@Override
	public List<ChartDTO> getChart(String standardAreaName, HttpServletRequest request) {
	    standardAreaName = standardAreaName.replaceAll("\"", "");
	    if (standardAreaName.equals("FirstChartLoad")) {
	        standardAreaName = sessionService.getSession(request);
	    }
	    StandardAreaDTO standardAreaDTO = standardAreaSelectOneService.selectOne(standardAreaName);
        List<ChartDTO> chartDTOList = chartMapper.chartDTOList(standardAreaDTO.getStdAreaId());
        return chartDTOList;
	  }    

}
