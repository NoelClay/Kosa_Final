package com.dms.datamodelmanagementserver.single.word.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.dms.datamodelmanagementserver.single.word.dto.WordDTO;

@Mapper
public interface WordMapper {
    boolean insertWord(WordDTO wordDTO);

    boolean isDuplicate(WordDTO wordDTO);

    WordDTO getWordAndTermInfo(@Param("dicId") String dicId);

    boolean updateWord(WordDTO wordDTO);

    boolean deleteWordAndTerm(@Param("deleteDicId")String deleteDicId);


}
