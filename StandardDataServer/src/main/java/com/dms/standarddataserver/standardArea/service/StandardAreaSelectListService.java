package com.dms.standarddataserver.standardArea.service;

import com.dms.standarddataserver.standardArea.dto.StandardAreaDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

public interface StandardAreaSelectListService {
    List<StandardAreaDTO> selectList(String userId);
}
