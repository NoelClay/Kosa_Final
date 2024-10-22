package com.dms.standarddataserver.bulk.domain.mapper;

import com.dms.standarddataserver.bulk.domain.dto.DomainExcelDataDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BulkDomainMapper {

    public String getRegisteredDomainGroupId(@Param("domainGroupName") String domainGroupName, @Param("stdAreaId") String stdAreaId);

    public int isDuplicatedByDomainGroupId(@Param("domainName") String domainName, @Param("domainGroupId") String domainGroupId, @Param("stdAreaId") String stdAreaId);

    public int isDuplicated(@Param("domainName") String domainName, @Param("stdAreaId") String stdAreaId);

    public int insertBulkDomain(DomainExcelDataDTO domainExcelDataDTO);

    public String findDomainId(@Param("domainName") String domainName, @Param("stdAreaId") String stdAreaId);

    public String findDomainIdWithDomainGroupId(@Param("domainName") String domainName, @Param("domainGroupId") String domainGroupId, @Param("stdAreaId") String stdAreaId);

    public List<DomainExcelDataDTO> selectAllDomain(@Param("stdAreaId") String stdAreaId);

    public DomainExcelDataDTO findDomainByDomainId(@Param("domainId") String domainId, @Param("stdAreaId") String stdAreaId);

    public String findDomainGroupName(@Param("domainGroupId") String domainGroupId, @Param("stdAreaId") String stdAreaId);
   
    public int isCorrectDataType(String dataType);
    
}
