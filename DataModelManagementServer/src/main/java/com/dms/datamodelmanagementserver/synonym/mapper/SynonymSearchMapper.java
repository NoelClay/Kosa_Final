package com.dms.datamodelmanagementserver.synonym.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.dms.datamodelmanagementserver.synonym.dto.SynonymDTO;

import java.util.List;

@Mapper
public interface SynonymSearchMapper {
    List<SynonymDTO> selectList(SynonymDTO synonymDTO);

}
