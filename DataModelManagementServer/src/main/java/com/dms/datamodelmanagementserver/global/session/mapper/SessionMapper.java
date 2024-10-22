package com.dms.datamodelmanagementserver.global.session.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SessionMapper {
    void setSession(@Param("selectedStandardArea") String selectedStandardArea);
    String getSession();
}