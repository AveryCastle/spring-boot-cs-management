package com.example.cs.springbootcsmanagement.domains;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class QnATypeTest {

    @Test
    public void shouldConnectBiDirectional() {
        final String UPPER_QNA_TYPE_TITLE = "이용문의";
        final String SUB_QNA_TYPE_TITLE = "회원 인증";

        QnAType upperQnAType = QnAType.builder().title(UPPER_QNA_TYPE_TITLE).expoOrder(1).build();
        QnAType subQnAType = new QnAType(SUB_QNA_TYPE_TITLE, 1, upperQnAType);

        assertTrue(upperQnAType.getSubQnATypes().contains(subQnAType));
        assertEquals(UPPER_QNA_TYPE_TITLE, subQnAType.getUpperQnAType().getTitle());
    }
}
