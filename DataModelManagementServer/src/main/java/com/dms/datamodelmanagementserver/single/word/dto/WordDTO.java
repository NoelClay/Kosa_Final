package com.dms.datamodelmanagementserver.single.word.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class WordDTO {
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