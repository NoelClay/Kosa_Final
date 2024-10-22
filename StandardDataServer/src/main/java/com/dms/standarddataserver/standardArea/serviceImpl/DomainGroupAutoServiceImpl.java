package com.dms.standarddataserver.standardArea.serviceImpl;

import com.dms.standarddataserver.standardArea.dto.DomainGroupDTO;
import com.dms.standarddataserver.standardArea.enums.AutoDomainGroup;
import com.dms.standarddataserver.standardArea.mapper.DomainGroupAutoMapper;
import com.dms.standarddataserver.standardArea.service.DomainGroupInsertAutoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.stream.Stream;

@Service
@Slf4j
public class DomainGroupAutoServiceImpl implements DomainGroupInsertAutoService {
    private final DomainGroupAutoMapper domainGroupAutoMapper;

    public DomainGroupAutoServiceImpl(DomainGroupAutoMapper domainGroupAutoMapper) {
        this.domainGroupAutoMapper = domainGroupAutoMapper;
    }
    
    /**
     * [수정 이력]
     * 날짜: 2024-03-07
     * 수정자: 최유민
     * 수정 내용: 
     * - 메서드명에 Auto 단어 추가
     * - domainGroupAutoMapper.insertDomainGroup() 인자 변경
    **/
    @Override
	public void insertAutoDomainGroup(String standardAreaId) {
    	Stream.of(AutoDomainGroup.values())
    		.map(AutoDomainGroup::getAutoDomainGroupName)
    		.forEach(autoDomainGroupName -> {
    			DomainGroupDTO autoDomainGroupDto = DomainGroupDTO.builder()
    				    .domainGroupId(UUID.randomUUID().toString())
    				    .subjAreaId(standardAreaId)
    				    .domainGroupName(autoDomainGroupName)
    				    .domainGroupDesc(autoDomainGroupName)
    				    .build();
    			domainGroupAutoMapper.insertAutoDomainGroup(autoDomainGroupDto);
    		});
	}

//    @Override
//    public void insertDomainGroup(String standardAreaId) {
//        domainGroupAutoMapper.insertDomainGroup(UUID.randomUUID().toString(), standardAreaId);
//    }
}
