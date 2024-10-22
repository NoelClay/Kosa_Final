package com.dms.datamodelmanagementserver.member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dms.datamodelmanagementserver.global.CookieUtil;
import com.dms.datamodelmanagementserver.global.DataType;
import com.dms.datamodelmanagementserver.global.DomainType;
import com.dms.datamodelmanagementserver.global.LogDefault;
import com.dms.datamodelmanagementserver.global.UrlBuilder;
import com.dms.datamodelmanagementserver.member.dto.MemberDTO;
import com.dms.datamodelmanagementserver.member.service.MemberLoginService;
import com.dms.datamodelmanagementserver.standardArea.dto.StandardAreaDTO;
import com.dms.datamodelmanagementserver.standardArea.service.StandardAreaSelectListService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
@RequestMapping("/dms")
public class MemberLoginController {
    private final MemberLoginService memberLoginService;
    private final StandardAreaSelectListService standardAreaSelectListService;
    private final LogDefault logDefault;
    private final UrlBuilder urlBuilder;
    private final RedisTemplate<String, String> redisTemplate;
    
    private final String INCORRECT_STATE = "incorrect";
    private final String UNATHORIZED_STATE = "unauthorized";
    private final String DELETED_STATE = "deleted";
    private final String INCORRECT_ALERT_MESSAGE = "회원이 아니거나 아이디 또는 비밀번호가 틀립니다.";
    private final String UNAUTHORIZED_ALERT_MESSAGE = "현재 권한이 없습니다.";
    private final String DELETED_ALERT_MESSAGE = "탈퇴한 회원입니다.";

	public MemberLoginController(MemberLoginService memberLoginService,
			StandardAreaSelectListService standardAreaSelectListService, LogDefault logDefault, UrlBuilder urlBuilder,
			RedisTemplate<String, String> redisTemplate) {
		super();
		this.memberLoginService = memberLoginService;
		this.standardAreaSelectListService = standardAreaSelectListService;
		this.logDefault = logDefault;
		this.urlBuilder = urlBuilder;
		this.redisTemplate = redisTemplate;
	}

	@GetMapping(value = "/")
    public String welcomePage() {
		
        logDefault.logCurrentMethod();
        //model.addAttribute("isNotLogin", isNotLogin);
        
        return "/member/auth-login-basic";
    }

    @GetMapping(value = "/dashboard")
    public String mainPage(HttpServletRequest request, Model model) {
        logDefault.logCurrentMethod();        
       // model.addAttribute("stdList",sessionService.getSession(request)); 
        return "/dashboard/dashboard";
    }
    
    
    //로그인
    @PostMapping(value = "/login")
    public String authenticateMember(@ModelAttribute MemberDTO memberDTO, Model model, HttpSession session, HttpServletResponse response, HttpServletRequest request) throws IOException {
        logDefault.logCurrentMethod();   
	
	    MemberDTO resultMemberDTO = memberLoginService.authenticateMember(memberDTO);
	    
	    String serviceUrl = urlBuilder.buildServiceUrl("/dms/");

	    if (INCORRECT_STATE.equals(resultMemberDTO.getState())) {
	        alertMessage(response, INCORRECT_ALERT_MESSAGE);
	        return String.format("redirect:%s", serviceUrl);
	    } else if (DELETED_STATE.equals(resultMemberDTO.getState())) {
	        alertMessage(response, DELETED_ALERT_MESSAGE);
	        return String.format("redirect:%s", serviceUrl);
	    } else if (UNATHORIZED_STATE.equals(resultMemberDTO.getState())) {
	        alertMessage(response, UNAUTHORIZED_ALERT_MESSAGE);
	        return String.format("redirect:%s", serviceUrl);
	    }
	    
        String memberKey = UUID.randomUUID().toString();
        redisTemplate.opsForValue().set(memberKey, resultMemberDTO.getId());	
        redisTemplate.expire(memberKey, 1, TimeUnit.DAYS);
        CookieUtil.setCookie(response, memberKey); 
        
    	List<StandardAreaDTO> stdList = standardAreaSelectListService.selectList(memberKey);
	    session.setAttribute("stdList", stdList);
	    
	    // 로그인 성공시, 표준분류체계 목록 중 첫번째 표준분류체계가 cookie 와 redis 에 저장되도록 설정
	    //sessionService.setSession(stdList.get(0).getStdAreaNm(), session, response);
	    
	    model.addAttribute("domainTypes", Arrays.stream(DomainType.values())
	            .map(Enum::toString)
	            .collect(Collectors.toList()));
	    model.addAttribute("dataTypes", Arrays.stream(DataType.values())
	            .map(Enum::toString)
	            .collect(Collectors.toList()));
        
        return "/standardData/standardData";
    }
    
    @ResponseBody
    @GetMapping(value = "/logout")
    public Map<String,String> logout(HttpServletRequest request, HttpServletResponse response) {
    	logDefault.logCurrentMethod(); 

    	String memberCookieValue = CookieUtil.getCookie(request);
    	String subjAreaNameCookieValue = CookieUtil.getCookieBySubjAreaName(request);
    	memberLoginService.logout(memberCookieValue, subjAreaNameCookieValue, request, response);
    	Map<String, String> map = new HashMap<>();
    	map.put("result", "success");
    	 
    	return map;
    }
      
    //로그인 세션 가져오기
    @PostMapping(value = "/loginSession")
    public ResponseEntity<MemberDTO> loginSession(HttpServletRequest request) {
    	logDefault.logCurrentMethod();

    	MemberDTO memberDTO = MemberDTO.builder()
    			.id(CookieUtil.getCookie(request))
    			.subjAreaId(CookieUtil.getCookieBySubjAreaName(request))
    			.build();

    	MemberDTO resultMemberDTO = memberLoginService.loginSession(memberDTO);

    	return ResponseEntity.ok(resultMemberDTO);
//    	if (resultMemberDTO != null) {
//            return ResponseEntity.ok(resultMemberDTO);
//        } else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//        }
    }
    
    private void alertMessage(HttpServletResponse response, String message) throws IOException {
    	response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
    	PrintWriter out = response.getWriter();
		out.println("<script> alert('" + message + "');");
		out.println("history.go(-1); </script>"); 
		out.close();
    }
    
}
