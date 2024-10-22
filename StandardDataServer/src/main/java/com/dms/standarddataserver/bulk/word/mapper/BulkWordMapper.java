package com.dms.standarddataserver.bulk.word.mapper;

import com.dms.standarddataserver.bulk.word.dto.BulkWordDataDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BulkWordMapper {

    public int isDuplicatedInLogicalName(@Param("dicLogicalName") String dicLogicalName, @Param("stdAreaId") String stdAreaId);

    public int isDuplicatedInPhysicalName(@Param("dicPhysicalName") String dicPhysicalName, @Param("stdAreaId") String stdAreaId);

    public String validateDuplicatedInSynonym(@Param("dicLogicalName") String synonym, @Param("stdAreaId") String stdAreaId);

    public int insertBulkWord(BulkWordDataDTO bulkWordDataDTO);

}
