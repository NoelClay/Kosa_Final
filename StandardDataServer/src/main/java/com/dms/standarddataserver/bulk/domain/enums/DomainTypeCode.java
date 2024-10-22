package com.dms.standarddataserver.bulk.domain.enums;

public enum DomainTypeCode {
    CODE_0001("코드"),
    CODE_0002("번호"),
    CODE_0003("일반");

    private final String domainType;

    DomainTypeCode(String domainType) {
        this.domainType = domainType;
    }

    public String getDomainType() {
        return domainType;
    }
}

