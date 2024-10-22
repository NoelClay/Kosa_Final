package com.dms.standarddataserver.bulk.term.dto;

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
public class BulkTermExcelDataDTO {

    private String stdAreaId;
    private String tempDicLogicalName;
    private String dicDescription;
    private String keyDomainName;
    private String domainTypeCode;
    private String domainGroupName;
    private String domainGroupId;
    private String dataTypeCode;
    private Integer dataLength;
    private Integer dataScale;
    private String dicLogicalName;
    private String dicPhysicalName;
    private String domainName;
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

