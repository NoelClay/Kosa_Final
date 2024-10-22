package com.dms.standarddataserver.standardArea.mapper;

import com.dms.standarddataserver.standardArea.dto.StandardAreaDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface StandardAreaInsertMapper {
    void insert(@Param("standardAreaDTO") StandardAreaDTO standardAreaDTO);
}
