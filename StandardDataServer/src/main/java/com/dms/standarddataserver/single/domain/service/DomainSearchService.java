package com.dms.standarddataserver.single.domain.service;

import com.dms.standarddataserver.single.domain.dto.DomainDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DomainSearchService {

    List<DomainDTO> selectDomainList(@Param("stdAreaId") String stdAreaId, @Param("domainName")String domainName, @Param("keyDomName")String keyDomName);


}
