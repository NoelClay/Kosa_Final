package com.dms.datamodelmanagementserver.single.domain.serviceimpl;

import com.dms.datamodelmanagementserver.single.domain.dto.DomainDTO;
import com.dms.datamodelmanagementserver.single.domain.mapper.DomainMapper;
import com.dms.datamodelmanagementserver.single.domain.service.DomainSearchService;
import com.dms.datamodelmanagementserver.standardArea.service.StandardAreaSelectOneService;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DomainSearchServiceImpl implements DomainSearchService {

	private final DomainMapper domainMapper;
    private final StandardAreaSelectOneService standardAreaSelectOneService;

    public DomainSearchServiceImpl(DomainMapper domainMapper,
			StandardAreaSelectOneService standardAreaSelectOneService) {
		this.domainMapper = domainMapper;
		this.standardAreaSelectOneService = standardAreaSelectOneService;
	}

	@Override
    public List<DomainDTO> searchDomainNameByDomainNameRest(String stdAreaName, String domainName, String keyDomName) {
		String stdAreaId = standardAreaSelectOneService.selectOne(stdAreaName).getStdAreaId();
		
		List<DomainDTO> result;
        if (domainName.isEmpty()) {
            // 빈 도메인명일 경우 모든 도메인 정보를 가져옴
            result = domainMapper.selectAllDomains(stdAreaId, keyDomName);
        } else {
            result = domainMapper.selectDomainList(stdAreaId, domainName, keyDomName);
        }
        return result;
	}

}
