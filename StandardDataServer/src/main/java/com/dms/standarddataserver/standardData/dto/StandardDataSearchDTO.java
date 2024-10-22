package com.dms.standarddataserver.standardData.dto;

import lombok.Data;

@Data
public class StandardDataSearchDTO {
    private String standardAreaName;
    private String searchWord;
    private boolean word;
    private boolean attribute;
    private boolean entity;
    private boolean domain;
    private String domainType;
    private String dataType;
    private boolean term;
}
