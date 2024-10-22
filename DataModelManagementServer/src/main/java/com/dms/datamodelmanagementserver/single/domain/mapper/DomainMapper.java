package com.dms.datamodelmanagementserver.single.domain.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.dms.datamodelmanagementserver.single.domain.dto.DomainDTO;
import com.dms.datamodelmanagementserver.single.domain.dto.DomainGroupDTO;

import java.util.List;

@Mapper
public interface DomainMapper {

    boolean insertDomain(DomainDTO domainDTO);

    public List<DomainGroupDTO> getDomainGroup(String stdAreaId);

    public boolean isDuplicateDomain(DomainDTO domainDTO);
    
    public boolean isDuplicateDomainInSameDomGroup(DomainDTO domainDTO);

    public boolean isDuplicateDomainGroup(DomainGroupDTO domainGroupDTO);

    public List<DomainDTO> selectDomainList(@Param("stdAreaId") String stdAreaId, @Param("domainName")String domainName, @Param("keyDomName")String keyDomName);
    
    public List<DomainDTO> selectAllDomains(@Param("stdAreaId") String stdAreaId, @Param("keyDomName")String keyDomName);

    public DomainDTO getDomainInfo(@Param("domId") String domId);

    public boolean updateDomain(DomainDTO domainDTO);

    public boolean deleteDomain(DomainDTO domainDTO);

}