package com.dms.datamodelmanagementserver.single.domain.enums;

public enum DataTypeCode {
    CHAR("CHAR", "C"),
    NCHAR("NCHAR", "NC"),
    VARCHAR("VARCHAR", "V"),
    NVARCHAR("NVARCHAR", "NV"),
    BOOLEAN("BOOLEAN", "BOOL"),
    NUMBER("NUMBER", "N"),
    TIME("TIME", "TM"),
    TIMESTAMP("TIMESTAMP", "TMS"),
    DATE("DATE", "DT"),
    DATETIME("DATETIME", "DTM"),
    CLOB("CLOB", "CLOB"),
    NCLOB("NCLOB", "NCLOB"),
    BLOB("BLOB", "BLOB");

    private final String dataTypeCode;
    private final String codeValue;

    DataTypeCode(String dataTypeCode, String codeValue) {
        this.dataTypeCode = dataTypeCode;
        this.codeValue = codeValue;
    }

    public String getDataTypeCode() { return this.dataTypeCode; }
    public String getCodeValue() { return this.codeValue; }
}
