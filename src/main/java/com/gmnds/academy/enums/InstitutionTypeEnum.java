package com.gmnds.academy.enums;

public enum InstitutionTypeEnum {
    SCHOOL("school"),
    HIGHER("higher"),
    COURSE("course"),
    OTHER("other");

    private final String type;

    InstitutionTypeEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
