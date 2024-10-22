package com.dms.standarddataserver.standardArea.enums;

public enum AutoDomainGroup {
	AMOUNT("AMOUNT"),
	CODE("CODE"),
	MEASUREMENT("MEASUREMENT"),
	NAME("NAME"),
	NUMBER("NUMBER"),
	QUANTITY("QUANTITY"),
	RATE("RATE"),
	TEXT("TEXT"),
	TIME("TIME"),
	VALUE("VALUE");

    private final String autoDomainGroupName;

    AutoDomainGroup(String autoDomainGroupName) {
        this.autoDomainGroupName = autoDomainGroupName;
    }

    public String getAutoDomainGroupName() {
        return autoDomainGroupName;
    }
}

