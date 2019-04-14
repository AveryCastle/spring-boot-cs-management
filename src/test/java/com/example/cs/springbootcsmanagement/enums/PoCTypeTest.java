package com.example.cs.springbootcsmanagement.enums;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class PoCTypeTest {

    @Test
    public void ofLegacyCode() {
        assertEquals(PoCType.OSC, PoCType.ofLegacyCode(PoCType.OSC.getLegacyCode()));
        assertEquals(PoCType.OSB, PoCType.ofLegacyCode(PoCType.OSB.getLegacyCode()));

        Throwable throwable = assertThrows(RuntimeException.class, () -> PoCType.ofLegacyCode("NOT-FOUND"));
        assertEquals("PoC에 [NOT-FOUND]가 미존재합니다.", throwable.getMessage());
    }
}
