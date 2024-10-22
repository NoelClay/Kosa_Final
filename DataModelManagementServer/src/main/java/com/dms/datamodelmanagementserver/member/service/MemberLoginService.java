package com.dms.datamodelmanagementserver.member.service;

import com.dms.datamodelmanagementserver.member.dto.MemberDTO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface MemberLoginService {

	MemberDTO authenticateMember(MemberDTO memberDTO);
	
	public MemberDTO loginSession(MemberDTO memberDTO);

//	Boolean register(MemberDTO memberDTO);
	
	void logout(String memberCookieValue, String subjAreaNameCookieValue, HttpServletRequest request, HttpServletResponse response);
    

}
