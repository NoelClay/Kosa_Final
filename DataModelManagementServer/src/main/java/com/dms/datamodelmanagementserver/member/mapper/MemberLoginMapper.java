package com.dms.datamodelmanagementserver.member.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.dms.datamodelmanagementserver.member.dto.MemberDTO;


@Mapper
public interface MemberLoginMapper {
    public MemberDTO authenticateMember(@Param("memberDTO")MemberDTO memberDTO);
    public int authorizedMember(@Param("memberDTO")MemberDTO memberDTO);
    public String findIndividualIdAndSubjAreaIdTest(@Param("subjAreaId")String subjAreaId, @Param("usrId")String usrId);
//	public void registerMember(@Param("memberDTO")MemberDTO memberDTO) throws Exception;
}
