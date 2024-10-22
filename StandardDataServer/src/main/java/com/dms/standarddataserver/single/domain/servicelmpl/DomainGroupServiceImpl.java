package com.dms.standarddataserver.single.domain.servicelmpl;

import com.dms.standarddataserver.single.domain.dto.DomainGroupDTO;
import com.dms.standarddataserver.single.domain.mapper.DomainMapper;
import com.dms.standarddataserver.single.domain.service.DomainGroupService;
import com.dms.standarddataserver.standardArea.service.StandardAreaSelectOneService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class DomainGroupServiceImpl implements DomainGroupService {
    private final DomainMapper domainMapper;
    private final StandardAreaSelectOneService standardAreaSelectOneService;
    private final RedisTemplate<String, String> redisTemplate;

	public DomainGroupServiceImpl(DomainMapper domainMapper, StandardAreaSelectOneService standardAreaSelectOneService,
			RedisTemplate<String, String> redisTemplate) {
		this.domainMapper = domainMapper;
		this.standardAreaSelectOneService = standardAreaSelectOneService;
		this.redisTemplate = redisTemplate;
	}

	@Override
    public List<DomainGroupDTO> getDomainGroup(String subjAreaNameCookieValue) {
		
		String stdAreaId = String.valueOf(redisTemplate.opsForValue().get(subjAreaNameCookieValue));
		String selectStandardArea = standardAreaSelectOneService.selectOne(stdAreaId).getStdAreaId();

        return domainMapper.getDomainGroup(selectStandardArea);
    }
}


