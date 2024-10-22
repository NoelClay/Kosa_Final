package com.dms.standarddataserver.bulk.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
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

    public void updateReason(String reason) {
        if (this.reason == null) {
            this.reason = new ArrayList<>();
        }
        this.reason.add(reason);
    }

    public void updateDomainName(String domainName) {
        this.domainName = domainName;
    }
}
