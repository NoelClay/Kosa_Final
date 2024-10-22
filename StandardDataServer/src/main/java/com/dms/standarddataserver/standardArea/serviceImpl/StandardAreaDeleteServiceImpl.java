package com.dms.standarddataserver.standardArea.serviceImpl;

import com.dms.standarddataserver.authorization.individual.mapper.IndividualAuthorizationMapper;
import com.dms.standarddataserver.standardArea.dto.StandardAreaDTO;
import com.dms.standarddataserver.standardArea.mapper.DomainGroupAutoMapper;
import com.dms.standarddataserver.standardArea.mapper.StandardAreaDeleteMapper;
import com.dms.standarddataserver.standardArea.service.StandardAreaCheckDuplicateService;
import com.dms.standarddataserver.standardArea.service.StandardAreaDeleteService;
import com.dms.standarddataserver.standardArea.service.StandardAreaSelectOneService;

import org.springframework.stereotype.Service;

@Service
public class StandardAreaDeleteServiceImpl implements StandardAreaDeleteService {
    private final StandardAreaDeleteMapper standardAreaDeleteMapper;
    private final StandardAreaCheckDuplicateService standardAreaCheckDuplicateService;
    private final StandardAreaSelectOneService standardAreaSelectOneService;
    private final IndividualAuthorizationMapper individualAuthorizationMapper;
    private final DomainGroupAutoMapper domainGroupAutoMapper;
    

	public StandardAreaDeleteServiceImpl(StandardAreaDeleteMapper standardAreaDeleteMapper,
			StandardAreaCheckDuplicateService standardAreaCheckDuplicateService,
			StandardAreaSelectOneService standardAreaSelectOneService,
			IndividualAuthorizationMapper individualAuthorizationMapper, DomainGroupAutoMapper domainGroupAutoMapper) {
		this.standardAreaDeleteMapper = standardAreaDeleteMapper;
		this.standardAreaCheckDuplicateService = standardAreaCheckDuplicateService;
		this.standardAreaSelectOneService = standardAreaSelectOneService;
		this.individualAuthorizationMapper = individualAuthorizationMapper;
		this.domainGroupAutoMapper = domainGroupAutoMapper;
	}


	@Override
    public boolean delete(StandardAreaDTO standardAreaDTO) {
    	String StdAreaNm = standardAreaDTO.getStdAreaNm();
    	standardAreaDTO = standardAreaSelectOneService.selectOne(StdAreaNm);
    	
        standardAreaDeleteMapper.delete(standardAreaDTO);
        standardAreaDeleteMapper.deletsDomain(standardAreaDTO);
        standardAreaDeleteMapper.deletsDict(standardAreaDTO);
        standardAreaDeleteMapper.deletsTerm(standardAreaDTO);
        individualAuthorizationMapper.deleteSubjAreaInUsrAuth(standardAreaDTO.getStdAreaId());
        domainGroupAutoMapper.deleteSubjAreaInDomainGroup(standardAreaDTO.getStdAreaId());
        return !standardAreaCheckDuplicateService.checkIfDuplicate(standardAreaDTO.getStdAreaNm());
    }
}
