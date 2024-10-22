package com.dms.datamodelmanagementserver.standardData.dto;

import lombok.Data;

@Data
public class StandardDataDTO {
    private String dicId;
    private String standardClassification;
    private String synonym;
    private String division;
    private String logicalName;
    private String physicalName;
    private String physicalFullName;
    private String keyDomain;
    private String domainGroup;
    private String domainType;
    private String domainName;
    private String logicalDataType;
    private String length;
    private String scale;
    private String isStandard;
    private String attributeClassifier;
    private String entityClassifier;
    private String dataMin;
    private String dataMax;
    private String description;
    private String relatedTerm;
}
