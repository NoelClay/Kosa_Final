package com.dms.datamodelmanagementserver.bulk.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DomainExcelDataDTO {

    private String domainId;
    private String stdAreaId;
    private String keyDomainName;
    private String domainName;
    private String domainDescription;
    private String domainTypeCode;
    private String domainGroupName;
    private String domainGroupId;
    private String dataTypeCode;
    private Integer dataLength;
    private Integer dataScale;
    private Integer dataMin;
    private Integer dataMax;
    private String availableStartDate;
    private String availableEndDate;
    private boolean isError;
    private List<String> reason;

}
