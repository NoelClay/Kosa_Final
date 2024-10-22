package com.dms.standarddataserver.bulk.domain.enums;

public enum DataTypeCode {
    CHAR("C"),
    NCHAR("NC"),
    VARCHAR("V"),
    NVARCHAR("NV"),
    BOOLEAN("BOOL"),
    NUMBER("N"),
    TIME("TM"),
    TIMESTAMP("TMS"),
    DATE("DT"),
    DATETIME("DTM"),
    CLOB("CLOB"),
    NCLOB("NCLOB"),
    BLOB("BLOB");

    private final String dataTypeAbbreviation;
    DataTypeCode(String dataTypeAbbreviation) {
        this.dataTypeAbbreviation = dataTypeAbbreviation;
    }

    public String getDataTypeAbbreviation() {
        return dataTypeAbbreviation;
    }
}

