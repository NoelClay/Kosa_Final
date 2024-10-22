package com.dms.standarddataserver.single.domain.dto;

import lombok.Data;

@Data
public class DomainDTO {
    private String domId;
    private String selectStandardArea;
    private String keyDomName;
    private String domName;
    private String domDescription;
    private String domGroupId;
    private String domGroupName;
    private String domTypeCode;
    private String dataTypeCode;
    private Integer dataLen;
    private Integer dataScale;
    private Integer dataMin;
    private Integer dataMax;
    private String avalStartDate;
    private String avalEndDate;

}

