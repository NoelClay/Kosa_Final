package com.dms.datamodelmanagementserver.global.session.serviceImpl;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.dms.datamodelmanagementserver.global.CookieUtil;
import com.dms.datamodelmanagementserver.global.session.service.SessionService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;



@Service
public class SessionServiceImpl implements SessionService {

    private final RedisTemplate<String, String> redisTemplate;

    public SessionServiceImpl(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
    
    /**
     * [수정 이력]
     * 날짜: 2024-03-13
     * 수정자: 최유민
     * 수정 내용: 
     * - redis 에 저장되는 key 와 value 저장 방식 변경
     * - cookie 사용
    **/
    @Override
    public void setSession(String selectedStandardArea, HttpSession session, HttpServletResponse response) {
    	String subjAreaNameKey = UUID.randomUUID().toString();
        redisTemplate.opsForValue().set(subjAreaNameKey, selectedStandardArea);
        redisTemplate.expire(subjAreaNameKey, 1, TimeUnit.DAYS);
        CookieUtil.setCookieBySubjAreaName(response, subjAreaNameKey);
    }

    /**
     * [수정 이력]
     * 날짜: 2024-03-13
     * 수정자: 최유민
     * 수정 내용: cookie 에 저장된 데이터로 redis key 를 얻는 방식으로 전환 
    **/
    @Override
    public String getSession(HttpServletRequest request) {
    	if(!StringUtils.hasText(CookieUtil.getCookieBySubjAreaName(request))) {
    		return "프로젝트를 선택해 주세요.";
    	}
        return String.valueOf(redisTemplate.opsForValue().get(CookieUtil.getCookieBySubjAreaName(request)));
    }
}
