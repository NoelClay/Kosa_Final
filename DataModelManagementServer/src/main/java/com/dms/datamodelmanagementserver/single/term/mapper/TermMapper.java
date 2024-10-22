package com.dms.datamodelmanagementserver.single.term.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.dms.datamodelmanagementserver.single.term.dto.TermDTO;
import com.dms.datamodelmanagementserver.single.term.dto.TermDomainDTO;
import com.dms.datamodelmanagementserver.single.word.dto.WordDTO;

import java.util.List;

@Mapper
public interface TermMapper {
    List<WordDTO> selectWordList(@Param("stdAreaId") String stdAreaId, @Param("searchWord") String searchWord);

//    List<WordDTO> selectValidatedWordList(@Param("dicId") String dicId);

    void singleTermInsert(@Param("termDTOList") List<TermDTO> termDTOList);

    TermDomainDTO getTermInfo(@Param("dicId")String dicId);
    boolean updateSingleTerm(@Param("dicId") String dicId, @Param("domId") String domId, @Param("dicDesc") String dicDesc);





}
