package com.dms.standarddataserver.standardData.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StandardDomainDTO {
    private String domId;
    private String stdAreaId;
    private String keyDomNm;
    private String domNm;
    private String domDesc;
    private String domTypeCd;
    private String domGrpId;
    private String dataTypeCd;
    private String dataLen;
    private String dataScale;
    private String dataMin;
    private String dataMax;
    private String avalStDt;
    private String avalEndDt;
}