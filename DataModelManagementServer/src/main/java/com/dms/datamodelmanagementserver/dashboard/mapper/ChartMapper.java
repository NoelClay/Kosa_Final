package com.dms.datamodelmanagementserver.dashboard.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.dms.datamodelmanagementserver.dashboard.dto.ChartDTO;

import java.util.List;

@Mapper
public interface ChartMapper {
    List<ChartDTO> chartDTOList (@Param("standardAreaId")String standardAreaId);
}