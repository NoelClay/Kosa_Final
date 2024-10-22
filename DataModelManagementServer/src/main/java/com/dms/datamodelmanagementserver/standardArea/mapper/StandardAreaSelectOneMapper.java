package com.dms.datamodelmanagementserver.standardArea.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.dms.datamodelmanagementserver.standardArea.dto.StandardAreaDTO;

@Mapper
public interface StandardAreaSelectOneMapper {
    StandardAreaDTO selectOne(@Param("standardAreaName")String standardAreaName);
}
