package com.dms.datamodelmanagementserver.single.domain.serviceimpl;
import com.dms.datamodelmanagementserver.global.ApiRequestBuilder;
import com.dms.datamodelmanagementserver.global.CookieUtil;
import com.dms.datamodelmanagementserver.single.domain.dto.DomainGroupDTO;
import com.dms.datamodelmanagementserver.single.domain.mapper.DomainMapper;
import com.dms.datamodelmanagementserver.single.domain.service.DomainGroupService;
import com.dms.datamodelmanagementserver.standardArea.service.StandardAreaSelectOneService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class DomainGroupServiceImpl implements DomainGroupService {
	
	private final DomainMapper domainMapper;
	private final StandardAreaSelectOneService standardAreaSelectOneService;
    private final RedisTemplate<String, String> redisTemplate;
    
    public DomainGroupServiceImpl(DomainMapper domainMapper, StandardAreaSelectOneService standardAreaSelectOneService,
			RedisTemplate<String, String> redisTemplate) {
		super();
		this.domainMapper = domainMapper;
		this.standardAreaSelectOneService = standardAreaSelectOneService;
		this.redisTemplate = redisTemplate;
	}

	@Override
    public List<DomainGroupDTO> getDomainGroup(HttpServletRequest request) {
    	String subjAreaNameCookieValue = CookieUtil.getCookieBySubjAreaName(request);
    	if(subjAreaNameCookieValue == null) {
    		return null;
    	}
    	String stdAreaId = String.valueOf(redisTemplate.opsForValue().get(subjAreaNameCookieValue));
		String selectStandardArea = standardAreaSelectOneService.selectOne(stdAreaId).getStdAreaId();

        return domainMapper.getDomainGroup(selectStandardArea);
    }

}
