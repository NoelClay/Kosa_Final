package com.dms.standarddataserver.dashboard.serviceImpl;

import com.dms.standarddataserver.dashboard.dto.ChartDTO;
import com.dms.standarddataserver.dashboard.mapper.ChartMapper;
import com.dms.standarddataserver.dashboard.service.ChartService;
import com.dms.standarddataserver.standardArea.dto.StandardAreaDTO;
import com.dms.standarddataserver.standardArea.service.StandardAreaSelectOneService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ChartServiceImpl implements ChartService {
    private final StandardAreaSelectOneService standardAreaSelectOneService;
    private final ChartMapper chartMapper;

    public ChartServiceImpl(StandardAreaSelectOneService standardAreaSelectOneService, ChartMapper chartMapper) {
        this.standardAreaSelectOneService = standardAreaSelectOneService;
        this.chartMapper = chartMapper;
    }

    @Override
    public List<ChartDTO> getChart(String standardAreaName) {
        StandardAreaDTO standardAreaDTO = standardAreaSelectOneService.selectOne(standardAreaName);
        List<ChartDTO> chartDTOList = chartMapper.chartDTOList(standardAreaDTO.getStdAreaId());
        for (ChartDTO chartDTO : chartDTOList) {
            log.info(chartDTO.getElement());
            log.info(chartDTO.getCount());
        }
        return chartDTOList;
    }
}
