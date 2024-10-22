package com.dms.standarddataserver.single.domain.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class DomainGroupDTO {
    private String domGrpId;
    private String stdAreaId;
    private String domGrpNm;
    private String domGrpDesc;
    private String avalStDt;
    private String avalEndDt;
}
