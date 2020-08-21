package br.com.exemplo.springboot.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Gender {
    MALE((short)0),
    FEMALE((short)1);

    private final short code;

    Gender(final short code) {
        this.code = code;
    }

    @JsonValue
    public short getId() {
        return this.code;
    }
}
