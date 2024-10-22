package com.dms.standarddataserver.standardArea.serviceImpl;

import com.dms.standarddataserver.standardArea.dto.StandardAreaDTO;
import com.dms.standarddataserver.standardArea.mapper.StandardAreaSelectListMapper;
import com.dms.standarddataserver.standardArea.service.StandardAreaInsertService;
import com.dms.standarddataserver.standardArea.service.StandardAreaSelectListService;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StandardAreaSelectListServiceImpl implements StandardAreaSelectListService {
    private final StandardAreaSelectListMapper standardAreaSelectListMapper;
    private final RedisTemplate<String, String> redisTemplate;
    
    public StandardAreaSelectListServiceImpl(StandardAreaSelectListMapper standardAreaSelectListMapper,
			RedisTemplate<String, String> redisTemplate) {
		this.standardAreaSelectListMapper = standardAreaSelectListMapper;
		this.redisTemplate = redisTemplate;
	}

	@Override
    public List<StandardAreaDTO> selectList(String userId) {
    	String memberId = String.valueOf(redisTemplate.opsForValue().get(userId));
        return standardAreaSelectListMapper.selectList(memberId);
    }
	
}
