package com.example.cs.springbootcsmanagement.domains;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.cs.springbootcsmanagement.enums.PoCType;
import org.junit.jupiter.api.Test;

class PoCQnATypeTest {

    @Test
    public void shouldSaveWithPocAndQnAType() {
        // Given.
        PoC osc = new PoC(PoCType.OSC);

        QnAType useTip = QnAType.builder().title("이용하기").expoOrder(1).build();
        final String SIGNUP_AND_CANCEL = "회원 가입/탈퇴";
        QnAType signUpAnCancel = QnAType.builder().title(SIGNUP_AND_CANCEL).upperQnAType(useTip).expoOrder(1).build();

        // When.
        PoCQnAType pocQnAType = PoCQnAType.builder().poc(osc).qnaType(signUpAnCancel).expoOrder(1).build();

        // Then.
        assertEquals(PoCType.OSC.getDescription(), pocQnAType.getPoc().getName());
        assertEquals(SIGNUP_AND_CANCEL, pocQnAType.getQnaType().getTitle());
    }
}
