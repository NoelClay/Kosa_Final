package com.dms.standarddataserver.standardArea.mapper;

import com.dms.standarddataserver.standardArea.dto.StandardAreaDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StandardAreaSelectListMapper {
    List<StandardAreaDTO> selectList(String memberId);

}

