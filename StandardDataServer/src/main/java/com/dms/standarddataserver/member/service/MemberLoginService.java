package com.dms.standarddataserver.member.service;

import com.dms.standarddataserver.member.dto.MemberDTO;

public interface MemberLoginService {
    public MemberDTO authenticateMember(MemberDTO memberDTO);
    public MemberDTO loginSession(MemberDTO memberDTO);
}
