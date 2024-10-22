package com.dms.standarddataserver.standardArea.mapper;

import com.dms.standarddataserver.standardArea.dto.StandardAreaDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface StandardAreaDeleteMapper {
    void delete(@Param("standardAreaDTO")StandardAreaDTO standardAreaDTO);

	void deletsDomain(@Param("standardAreaDTO")StandardAreaDTO standardAreaDTO);

	void deletsDict(@Param("standardAreaDTO")StandardAreaDTO standardAreaDTO);

	void deletsTerm(@Param("standardAreaDTO")StandardAreaDTO standardAreaDTO);
}
