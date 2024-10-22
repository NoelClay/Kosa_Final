package com.dms.datamodelmanagementserver.member.serviceImpl;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.dms.datamodelmanagementserver.member.dto.MemberDTO;
import com.dms.datamodelmanagementserver.member.mapper.MemberLoginMapper;
import com.dms.datamodelmanagementserver.member.service.MemberLoginService;
import com.dms.datamodelmanagementserver.standardArea.service.StandardAreaSelectOneService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MemberLoginServiceImpl implements MemberLoginService {
	
	private final MemberLoginMapper memberLoginMapper;
	private final StandardAreaSelectOneService standardAreaSelectOneService;
	private final RedisTemplate<String, String> redisTemplate;
	
	public final String INCORRECT = "incorrect";
    public final String UNAUTHORIZED = "unauthorized";
    public final String DELETED = "deleted";
    public final String UNUSED = "N";
    
	public MemberLoginServiceImpl(MemberLoginMapper memberLoginMapper,
			StandardAreaSelectOneService standardAreaSelectOneService, RedisTemplate<String, String> redisTemplate) {
		this.memberLoginMapper = memberLoginMapper;
		this.standardAreaSelectOneService = standardAreaSelectOneService;
		this.redisTemplate = redisTemplate;
	}
	
	//로그인
	@Override
	public MemberDTO authenticateMember(MemberDTO memberDTO) {
		
		if (memberLoginMapper.authenticateMember(memberDTO) == null) {
    		memberDTO.setState(INCORRECT);
    		return memberDTO;
    	} else if (memberLoginMapper.authenticateMember(memberDTO) != null && UNUSED.equals(memberLoginMapper.authenticateMember(memberDTO).getUseYn())) {
    		memberDTO.setState(DELETED);
    		return memberDTO;
    	} else if (memberLoginMapper.authorizedMember(memberDTO) == 0) {
    		memberDTO.setState(UNAUTHORIZED);
    		return memberDTO;
    	} 
		
        return memberLoginMapper.authenticateMember(memberDTO) ;	
	}
	
	@Override
	public MemberDTO loginSession(MemberDTO memberDTO) {        
		String id = memberDTO.getId();
        String subjAreaName = memberDTO.getSubjAreaId();
        
        if (!StringUtils.hasText(id)) {
            return null;
        }

        if (redisTemplate.opsForValue().get(id) == null) {
        	return null;
        }
        
        String userId = redisTemplate.opsForValue().get(id);
        
        if (StringUtils.hasText(subjAreaName)) {
        	if (redisTemplate.opsForValue().get(subjAreaName) == null) {
        		return null;
        	}
        	// standardAreaSelectOneTest.xml 에 연결되어 있기 때문에 모두 옮긴 후, standardAreaSelectOne.xml 으로 변경 필요
            String subjAreaId = standardAreaSelectOneService.selectOne(redisTemplate.opsForValue().get(subjAreaName)).getStdAreaId();
            // login.xml 에 연결되어 있기 때문에 모두 옮긴 후, individualAuthorization.xml 으로 변경 필요
            String authTpCd = memberLoginMapper.findIndividualIdAndSubjAreaIdTest(subjAreaId, userId);
            return MemberDTO.builder()
                    .id(userId)
                    .subjAreaId(subjAreaId)
                    .authTpCd(authTpCd)
                    .build();
        }
        return MemberDTO.builder()
                .id(userId)
                .build();		
	}

	@Override
	public void logout(String memberCookieValue, String subjAreaNameCookieValue, HttpServletRequest request, HttpServletResponse response) {
		deleteRedisValue(memberCookieValue, subjAreaNameCookieValue);	
	}
	
    private void deleteRedisValue(String memberCookieValue, String subjAreaNameCookieValue) {
        ValueOperations<String, String> opsForValue = redisTemplate.opsForValue();
        opsForValue.getOperations().delete(memberCookieValue);
        if(StringUtils.hasText(subjAreaNameCookieValue)) {
        	opsForValue.getOperations().delete(subjAreaNameCookieValue);
        }
    }
}

