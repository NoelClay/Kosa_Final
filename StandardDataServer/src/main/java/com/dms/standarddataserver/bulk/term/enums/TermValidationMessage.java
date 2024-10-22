package com.dms.standarddataserver.bulk.term.enums;

public enum TermValidationMessage {
    REQUIRED_FIELD_MISSING("필수항목 누락"),
    CELL_ERROR("셀에 유효하지 않은 값이 포함"),
    TERM_NAME_DUPLICATE_IN_EXCEL("엑셀 내 용어명 중복"),
    TERM_NAME_DUPLICATE_IN_DB("용어명 중복(등록된 용어)"),
    NOT_REGISTERED_DOMAIN("등록되지 않은 도메인명(도메인 등록 필요)"),
    NOT_REGISTERED_DOMAIN_IN_DOMAIN_GROUP("동일한 도메인그룹 내 등록되지 않은 도메인명"),
    NOT_REGISTERED_WORD("등록되지 않은 표준단어(표준단어 등록 필요)"),
    NOT_STANDARD_WORD("비표준 단어"),
    NOT_ATTRIBUTE_SUFFIX("속성분류어에 해당하지 않는 단어"),
    VALIDATION_PASS("!Y!");

    private final String message;

    TermValidationMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}

