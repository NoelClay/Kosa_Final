package com.dms.datamodelmanagementserver.bulk.word.dto;

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
public class BulkWordDataDTO {

    private String dicId;
    private String stdAreaId;
    private String altDicId;
    private String dicGbnCd;
    private String dicLogicalName;
    private String dicPhysicalName;
    private String dicPhysicalFullName;
    private String entitySuffix;
    private String attributeSuffix;
    private String standardCheck;
    private List<String> synonym;
    private String dicDescription;
    private List<String> reason;

    public void updateReason(String reason) {
        if (this.reason == null) {
            this.reason = new ArrayList<>();
        }
        this.reason.add(reason);
    }

}

