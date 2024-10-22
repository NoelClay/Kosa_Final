package com.dms.standarddataserver.standardData.mapper;

import com.dms.standarddataserver.standardData.dto.StandardDataDTO;
import com.dms.standarddataserver.standardData.dto.StandardDataSearchDTO;
import com.dms.standarddataserver.standardData.dto.StandardDictionaryDTO;
import com.dms.standarddataserver.standardData.dto.StandardDomainDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StandardDataSearchMapper {
    List<StandardDictionaryDTO> searchDictionaryByStandardArea(@Param("standardAreaId") String standardAreaId, @Param("standardDataSearchDTO") StandardDataSearchDTO standardDataSearchDTO);

    StandardDictionaryDTO searchDictionaryDTOByDictionaryId(@Param("standardDataDTO") StandardDataDTO standardDataDTO);

    StandardDomainDTO searchStandardDomainDTOByDomainId(@Param("domId") String domId);

    List<StandardDomainDTO> searchStandardDomainDTOList(@Param("standardAreaId") String standardAreaId);

    List<StandardDomainDTO> searchStandardDomainDTOListByDomainType(@Param("standardAreaId") String standardAreaId, @Param("domainType") String domainType);

    List<StandardDomainDTO> searchStandardDomainDTOListByDataType(@Param("standardAreaId") String standardAreaId, @Param("dataType") String dataType);

    List<StandardDomainDTO> searchStandardDomainDTOListByDomainTypeAndDataType(@Param("standardAreaId") String standardAreaId, @Param("domainType") String domainType, @Param("dataType") String dataType);

    List<StandardDictionaryDTO> checkSynonym(@Param("standardDataDTO") StandardDataDTO standardDataDTO);
    
    List<StandardDictionaryDTO> getSynonymList(@Param("standardAreaId") String standardAreaId);

    String selectDomainGroupByDomainGroupId(@Param("standardDomainGroupId") String standardDomainGroupId);

    List<String> selectRelatedTermByLogicalName(@Param("logicalName") String logicalName, @Param("standardAreaId") String standardAreaId);

    List<String> selectRelatedTermByDomainId(@Param("domainId") String domainId, @Param("standardAreaId") String standardAreaId);
}