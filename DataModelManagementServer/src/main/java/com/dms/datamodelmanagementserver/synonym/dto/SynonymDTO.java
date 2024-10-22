package com.dms.datamodelmanagementserver.synonym.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SynonymDTO {
    private String dicId;
    private String stdAreaId;
    private String stdAreaNm; // STD_AREA 테이블에서 갖고옴
    private String altDicId;// SynonymDTO의 dicId
    private String dicGbnCd;
    private String dicLogNm;
    private String dicPhyNm;
    private String dicPhyFLLNm;
    private String dicDesc;
    private String entClssYn;
    private String attrClssYn;
    private String standardYn;
    private String domId;
    private String avalStDt;
    private String avalEndDt;
    private String grpNo;
//    private String stdLogNm;//표준분류체계에서 가져온 논리명






}
