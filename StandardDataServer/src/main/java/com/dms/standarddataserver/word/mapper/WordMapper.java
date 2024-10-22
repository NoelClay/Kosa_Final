package com.dms.standarddataserver.word.mapper;

import com.dms.standarddataserver.word.dto.WordDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface WordMapper {
    boolean insertWord(WordDTO wordDTO);

    boolean isDuplicate(WordDTO wordDTO);

    WordDTO getWordAndTermInfo(@Param("dicId") String dicId);

    boolean updateWord(WordDTO wordDTO);

    boolean deleteWordAndTerm(@Param("deleteDicId")String deleteDicId);


}
