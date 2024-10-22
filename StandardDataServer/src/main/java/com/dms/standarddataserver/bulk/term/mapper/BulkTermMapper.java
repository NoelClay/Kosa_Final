package com.dms.standarddataserver.bulk.term.mapper;

import com.dms.standarddataserver.bulk.term.dto.BulkTermDataDTO;
import com.dms.standarddataserver.bulk.term.dto.WordOfTermDTO;
import com.dms.standarddataserver.bulk.word.dto.BulkWordDataDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BulkTermMapper {

    public int isDuplicatedInLogicalName(@Param("dicLogicalName") String dicLogicalName, @Param("stdAreaId") String stdAreaId);

    public String checkRegisteredWord(@Param("dicLogicalName") String word, @Param("stdAreaId") String stdAreaId);

    public BulkWordDataDTO checkLastWordOfTerm(@Param("dicLogicalName") String word, @Param("stdAreaId") String stdAreaId);

    public String findPhysicalNameOfWord(@Param("dicLogicalName") String word, @Param("stdAreaId") String stdAreaId);

    public int insertBulkTerm(BulkTermDataDTO bulkTermDataDTO);

    public String findDicIdByWordName(@Param("dicLogicalName") String word, @Param("stdAreaId") String stdAreaId);

    public int insertWordOfTerm(WordOfTermDTO wordOfTermDTO);

}
