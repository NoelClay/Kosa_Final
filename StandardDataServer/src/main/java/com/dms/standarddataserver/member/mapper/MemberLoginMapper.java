package com.dms.standarddataserver.member.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.dms.standarddataserver.member.dto.MemberDTO;

@Mapper
public interface MemberLoginMapper {
    public MemberDTO authenticateMember(@Param("memberDTO")MemberDTO memberDTO);
    public int authorizedMember(@Param("memberDTO")MemberDTO memberDTO);
	public void registerMember(@Param("memberDTO")MemberDTO memberDTO) throws Exception;
}
