package com.dms.standarddataserver.member.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberDTO implements Serializable{
    private String id;
    private String password;
    private String name;
    private String subjAreaId;
    private String authTpCd;
    private String useYn;
    private String state;
}
