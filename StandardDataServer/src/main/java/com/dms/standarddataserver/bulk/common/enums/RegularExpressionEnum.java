package com.dms.standarddataserver.bulk.common.enums;

public enum RegularExpressionEnum {
    ENGLISH_AND_KOREAN_NO_SPECIAL_CHARACTERS("^[a-zA-Z가-힣\\d\\s]*$"),
    ENGLISH_ONLY("^[a-zA-Z]*$"),
    ENGLISH_WITH_SPECIAL_CHARACTERS("^[a-zA-Z\\d\\s!\"#$%&'()*+,-./:;<=>?@[\\\\]^_`{|}~]*$");

    private final String regex;

    RegularExpressionEnum(String regex) {
        this.regex = regex;
    }

    public String getRegex() {
        return regex;
    }

}

