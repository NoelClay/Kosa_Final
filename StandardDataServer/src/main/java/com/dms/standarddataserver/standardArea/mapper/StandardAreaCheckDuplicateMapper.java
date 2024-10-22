package com.dms.standarddataserver.standardArea.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface StandardAreaCheckDuplicateMapper {
    Boolean checkIfDuplicate(@Param("stdAreaName") String standardAreaName);
}
