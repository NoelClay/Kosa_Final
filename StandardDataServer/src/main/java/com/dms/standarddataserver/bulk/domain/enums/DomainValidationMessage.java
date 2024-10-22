package com.dms.standarddataserver.bulk.domain.enums;

public enum DomainValidationMessage {
    REQUIRED_FIELD_MISSING("필수항목 누락"),
    CELL_ERROR("셀에 유효하지 않은 값이 포함"),
    DOMAIN_TYPE_MISMATCH("도메인유형 불일치"),
    DATA_TYPE_MISMATCH("데이터타입 불일치"),
    NOT_REGISTERED_IN_DOMAIN_GROUP("미등록 도메인그룹"),
    DOMAIN_NAME_DUPLICATE_IN_EXCEL_NOT_DOMAIN_GROUP("엑셀 내 도메인명 중복(도메인그룹 미지정)"),
    DOMAIN_NAME_DUPLICATE_IN_EXCEL_EXIST_DOMAIN_GROUP("엑셀 내 동일한 도메인그룹에서 도메인명 중복"),
    DOMAIN_NAME_DUPLICATE_IN_DOMAIN_GROUP("동일한 도메인그룹 내 도메인명 중복(등록된 도메인)"),
    DOMAIN_NAME_DUPLICATE("도메인명 중복(등록된 도메인)"),
    VALIDATION_PASS("!Y!");

    private final String message;

    DomainValidationMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}

