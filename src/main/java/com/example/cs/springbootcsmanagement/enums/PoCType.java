package com.example.cs.springbootcsmanagement.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum PoCType {

    OSC("OSC 설명", "11111"), OSB("OSB 설명", "22222");

    private String description;

    private String legacyCode;

    PoCType(String description, String legacyCode) {
        this.description = description;
        this.legacyCode = legacyCode;
    }

    public static PoCType ofLegacyCode(String legacyCode) {
        // TODO: 의미있는 Exception 생성해서 바꾸기.
        return Arrays.stream(PoCType.values())
                .filter(value -> value.getLegacyCode().equals(legacyCode))
                .findAny()
                .orElseThrow(() -> new RuntimeException(String.format("PoC에 [%s]가 미존재합니다.", legacyCode)));
    }
}
