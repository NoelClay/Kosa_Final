package com.dms.standarddataserver.term.mapper;

import com.dms.standarddataserver.term.dto.TermDTO;
import com.dms.standarddataserver.term.dto.TermDomainDTO;
import com.dms.standarddataserver.word.dto.WordDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TermMapper {
    List<WordDTO> selectWordList(@Param("stdAreaId") String stdAreaId, @Param("searchWord") String searchWord);

//    List<WordDTO> selectValidatedWordList(@Param("dicId") String dicId);

    void singleTermInsert(@Param("termDTOList") List<TermDTO> termDTOList);

    TermDomainDTO getTermInfo(@Param("dicId")String dicId);
    boolean updateSingleTerm(@Param("dicId") String dicId, @Param("domId") String domId, @Param("dicDesc") String dicDesc);





}
