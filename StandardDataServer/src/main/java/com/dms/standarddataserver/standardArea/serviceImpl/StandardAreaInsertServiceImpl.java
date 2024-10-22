package com.dms.standarddataserver.standardArea.serviceImpl;

import com.dms.standarddataserver.authorization.individual.mapper.IndividualAuthorizationMapper;
import com.dms.standarddataserver.standardArea.dto.StandardAreaDTO;
import com.dms.standarddataserver.standardArea.mapper.StandardAreaInsertMapper;
import com.dms.standarddataserver.standardArea.service.DomainGroupInsertAutoService;
import com.dms.standarddataserver.standardArea.service.StandardAreaInsertService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class StandardAreaInsertServiceImpl implements StandardAreaInsertService {
    private final StandardAreaInsertMapper standardAreaInsertMapper;
    private final DomainGroupInsertAutoService domainGroupInsertAutoService;
    private final IndividualAuthorizationMapper individualAuthorizationMapper;
    private final RedisTemplate<String, String> redisTemplate;
    private final static String ADMIN = "admin";

    public StandardAreaInsertServiceImpl(StandardAreaInsertMapper standardAreaInsertMapper,
			DomainGroupInsertAutoService domainGroupInsertAutoService,
			IndividualAuthorizationMapper individualAuthorizationMapper, RedisTemplate<String, String> redisTemplate) {
		this.standardAreaInsertMapper = standardAreaInsertMapper;
		this.domainGroupInsertAutoService = domainGroupInsertAutoService;
		this.individualAuthorizationMapper = individualAuthorizationMapper;
		this.redisTemplate = redisTemplate;
	}

	@Override
    public void insert(StandardAreaDTO standardAreaDTO) {
        standardAreaDTO.setStdAreaId(UUID.randomUUID().toString());
        domainGroupInsertAutoService.insertAutoDomainGroup(standardAreaDTO.getStdAreaId());

        standardAreaDTO.setUserId(String.valueOf(redisTemplate.opsForValue().get(standardAreaDTO.getMemberKey())));
        
        standardAreaInsertMapper.insert(standardAreaDTO);
		if(ADMIN.equals(standardAreaDTO.getUserId())) {
			String subjAreaId = standardAreaDTO.getStdAreaId();
			String usrId = standardAreaDTO.getUserId();
			individualAuthorizationMapper.insertAdminAuth(subjAreaId, usrId);
        }
    }
}
