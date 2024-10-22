package com.dms.standarddataserver.bulk.word.enums;

public enum WordValidationMessage {
    REQUIRED_FIELD_MISSING("필수항목 누락"),
    CELL_ERROR("셀에 유효하지 않은 값이 포함"),
    NO_SPECIAL_CHARACTERS("논리명 특수문자 사용 불가"),
    NOT_ONLY_ENGLISH_NO_SPECIAL_CHARACTERS("물리명 영문 미사용(특수문자 사용 불가)"),
    NOT_ONLY_ENGLISH("영문풀네임 영문 미사용"),
    LOGICAL_NAME_DUPLICATE_IN_EXCEL("엑셀 내 단어논리명 중복"),
    LOGICAL_NAME_DUPLICATE("단어논리명 중복(등록된 단어)"),
    PHYSICAL_NAME_DUPLICATE_IN_EXCEL("엑셀 내 단어물리명 중복"),
    PHYSICAL_NAME_DUPLICATE("단어물리명 중복(등록된 단어)"),
    PHYSICAL_FULL_NAME_DUPLICATE_IN_EXCEL("엑셀 내 단어영문풀네임 중복"),
    PHYSICAL_FULL_NAME_DUPLICATE("단어영문풀네임 중복(등록된 단어)"),
    STANDARD_LOGICAL_NAME_DUPLICATE_IN_SYNONYM("표준단어로 등록된 동의어"),
    NONSTANDARD_LOGICAL_NAME_DUPLICATE_IN_SYNONYM("비표준단어로 등록된 동의어"),
    NOT_PERMITTED_BOTH_STANDARD_NONSTANDARD("비표준단어 또는 표준단어 중 하나의 형식만 등록 가능"),
    VALIDATION_PASS("!Y!");

    private final String message;

    WordValidationMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}

