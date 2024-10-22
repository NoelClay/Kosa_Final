package com.dms.standarddataserver.standardArea.dto;

import lombok.Data;

@Data
public class StandardAreaDTO {

    private String stdAreaId;
    private String stdAreaNm;
    private String stdAreaDesc;
    private String userId;
    private String avalStDt;
    private String avalEndDt;
    private String message;
    /**
     * [수정 이력]
     * 날짜: 2024-03-12
     * 수정자: 최유민
     * 수정 내용: cookie 의 memberKey 의 value 저장하기 위한 memberKey 생성
    **/
    private String memberKey;

}
