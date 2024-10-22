package com.dms.standarddataserver.member.serviceImpl;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.dms.standarddataserver.authorization.individual.mapper.IndividualAuthorizationMapper;
import com.dms.standarddataserver.member.dto.MemberDTO;
import com.dms.standarddataserver.member.mapper.MemberLoginMapper;
import com.dms.standarddataserver.member.service.MemberLoginService;
import com.dms.standarddataserver.standardArea.service.StandardAreaSelectOneService;

@Service
public class MemberLoginServiceImpl implements MemberLoginService {
    public final MemberLoginMapper memberLoginMapper;
    private final IndividualAuthorizationMapper individualAuthorizationMapper;
    private final StandardAreaSelectOneService standardAreaSelectOneService;
    private final RedisTemplate<String, String> redisTemplate;
    
    public final String INCORRECT = "incorrect";
    public final String UNAUTHORIZED = "unauthorized";
    public final String DELETED = "deleted";
    public final String UNUSED = "N";

	public MemberLoginServiceImpl(MemberLoginMapper memberLoginMapper,
			IndividualAuthorizationMapper individualAuthorizationMapper,
			StandardAreaSelectOneService standardAreaSelectOneService, RedisTemplate<String, String> redisTemplate) {
		this.memberLoginMapper = memberLoginMapper;
		this.individualAuthorizationMapper = individualAuthorizationMapper;
		this.standardAreaSelectOneService = standardAreaSelectOneService;
		this.redisTemplate = redisTemplate;
	}

	@Override
    public MemberDTO authenticateMember(MemberDTO memberDTO) {
    	if (memberLoginMapper.authenticateMember(memberDTO) == null) {
    		memberDTO.setState(INCORRECT);
    		return memberDTO;
    	} else if (memberLoginMapper.authorizedMember(memberDTO) == 0) {
    		memberDTO.setState(UNAUTHORIZED);
    		return memberDTO;
    	} else if (memberLoginMapper.authenticateMember(memberDTO) != null && UNUSED.equals(memberLoginMapper.authenticateMember(memberDTO).getUseYn())) {
    		memberDTO.setState(DELETED);
    		return memberDTO;
    	}
    	
        return memberLoginMapper.authenticateMember(memberDTO) ;
    }
    
    @Override
    public MemberDTO loginSession(MemberDTO memberDTO) {
        String id = memberDTO.getId();
        String subjAreaName = memberDTO.getSubjAreaId();

        System.out.println("id , subjAreaName"+ "|" + id + "|"+ subjAreaName);
       
        
        if (!StringUtils.hasText(id)) {
        	   System.out.println("!StringUtils.hasText(id)");
            return null;
        }

        if (redisTemplate.opsForValue().get(id) == null) {
        	   System.out.println("redisTemplate.opsForValue().get(id)");
        	return null;
        }
        
        String userId = redisTemplate.opsForValue().get(id);
        System.out.println("userId" + userId);
        
        
        if (StringUtils.hasText(subjAreaName)) {
        	   System.out.println(StringUtils.hasText(subjAreaName));
        	if (redisTemplate.opsForValue().get(subjAreaName) == null) {
        		   System.out.println("redisTemplate.opsForValue().get(subjAreaName)");
        		return null;
        	}
            String subjAreaId = standardAreaSelectOneService.selectOne(redisTemplate.opsForValue().get(subjAreaName)).getStdAreaId();
            String authTpCd = individualAuthorizationMapper.findIndividualIdAndSubjAreaId(subjAreaId, userId);
            System.out.println("subjAreaId , authTpCd"+ "|" + subjAreaId + "|"+ authTpCd);
            return MemberDTO.builder()
                    .id(userId)
                    .subjAreaId(subjAreaId)
                    .authTpCd(authTpCd)
                    .build();
        }

        System.out.println("MemberDTO.builder().id(userId).build()  " +  MemberDTO.builder()
        .id(userId)
        .build());
        return MemberDTO.builder()
                .id(userId)
                .build();
    }

}
