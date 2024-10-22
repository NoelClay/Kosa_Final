package com.dms.standarddataserver.bulk.word.dto;

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
public class WordExcelDataDTO {

    private String stdAreaId;
    private String dicLogicalName;
    private String dicPhysicalName;
    private String dicPhysicalFullName;
    private String entitySuffix;
    private String attributeSuffix;
    private List<String> synonym;
    private String dicDescription;
    private boolean isError;
    private List<String> reason;

    public void updateReason(String reason) {
        if (this.reason == null) {
            this.reason = new ArrayList<>();
        }
        this.reason.add(reason);
    }

}

