package com.dms.standarddataserver.standardData.dto;

import lombok.Data;

@Data
public class StandardDictionaryDTO {

    private String dicId;
    private String stdAreaId;
    private String altDicId;
    private String dicGbnCd;
    private String dicLogNm;
    private String dicPhyNm;
    private String dicPhyFllNm;
    private String dicDesc;
    private String entClssYn;
    private String attrClssYn;
    private String standardYn;
    private String domId;
    private String avalStDt;
    private String avalEndDt;
}
