package com.dms.standarddataserver.bulk.word.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BulkWordDataDTO {

    private String dicId;
    private String stdAreaId;
    private String alternativeDicId;
    private String dicGbnCd;
    private String dicLogicalName;
    private String dicPhysicalName;
    private String dicPhysicalFullName;
    private String entitySuffix;
    private String attributeSuffix;
    private String checkedStandard;
    private List<String> synonym;
    private String dicDescription;
    private List<String> reason;

}

