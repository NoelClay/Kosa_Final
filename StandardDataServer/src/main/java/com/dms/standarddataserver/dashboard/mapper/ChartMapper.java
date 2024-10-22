package com.dms.standarddataserver.dashboard.mapper;

import com.dms.standarddataserver.dashboard.dto.ChartDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ChartMapper {
    List<ChartDTO> chartDTOList (@Param("standardAreaId")String standardAreaId);
}