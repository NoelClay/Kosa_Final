package com.dms.standarddataserver.synonym.mapper;


import com.dms.standarddataserver.synonym.dto.SynonymDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SynonymSearchMapper {
    List<SynonymDTO> selectList(SynonymDTO synonymDTO);

}
