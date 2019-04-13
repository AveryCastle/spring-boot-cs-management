package com.example.cs.springbootcsmanagement.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.example.cs.springbootcsmanagement.domains.QnAType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@SpringBootTest
class QnATypeRepositoryITest {

    private static final String USE_TIP_TITLE = "이용문의";
    private static final String PAYMENT_TITLE = "구매/결제";
    private static final String DEVICE_TITLE = "단말";

    private static final String SIGNUP_AND_CANCEL_TITLE = "회원 가입/탈퇴";
    private static final String AUTHENTICATION_TITLE = "회원인증";
    private static final String CONNECTION_ERROR = "접속 실패/오류";

    private Long useTipId;

    @Autowired
    private QnATypeRepository qnaTypeRepository;

    @BeforeEach
    @Transactional
    public void setUp() {
        QnAType useTip = QnAType.builder().title(USE_TIP_TITLE).expoOrder(2).build();
        QnAType payment = QnAType.builder().title(PAYMENT_TITLE).expoOrder(3).build();
        QnAType device = QnAType.builder().title(DEVICE_TITLE).expoOrder(1).build();

        qnaTypeRepository.saveAll(Arrays.asList(useTip, payment, device));

        useTipId = useTip.getId();

        QnAType signUpAndCancel = QnAType.builder().title(SIGNUP_AND_CANCEL_TITLE).expoOrder(3).upperQnAType(useTip).build();
        QnAType authentication = QnAType.builder().title(AUTHENTICATION_TITLE).expoOrder(1).upperQnAType(useTip).build();
        QnAType connectionError = QnAType.builder().title(CONNECTION_ERROR).expoOrder(2).upperQnAType(useTip).build();

        qnaTypeRepository.saveAll(Arrays.asList(signUpAndCancel, authentication, connectionError));

        QnAType purchaseHistory = QnAType.builder().title("구매내역").expoOrder(1).upperQnAType(payment).build();
        QnAType purchaseAndCancel = QnAType.builder().title("구매/취소문의").expoOrder(2).upperQnAType(payment).build();

        qnaTypeRepository.saveAll(Arrays.asList(purchaseHistory, purchaseAndCancel));

        QnAType supportDevice = QnAType.builder().title("단말지원").expoOrder(1).upperQnAType(device).build();
        qnaTypeRepository.save(supportDevice);

        qnaTypeRepository.flush();
    }

    @Test
    public void givenQnATypes_whenFindAllByUpperQnAType_thenFetchAllEntityGraphOrderByExpoOrd() {
        List<QnAType> qnaTypes = qnaTypeRepository.findAllWithSubQnATypesOrderByExpoOrder();

        assertEquals(6, qnaTypes.size());

        QnAType device = qnaTypes.get(0);
        QnAType useTip = qnaTypes.get(1);
        QnAType payment = qnaTypes.get(2);

        assertEquals(DEVICE_TITLE, device.getTitle());
        assertEquals(USE_TIP_TITLE, useTip.getTitle());
        assertEquals(PAYMENT_TITLE, payment.getTitle());

        assertTrueUseTipSubQnATypeOrdering(useTip);
    }

    @Test
    public void givenQnATypes_whenFindByUpperQnAType_thenFetchSubQnATypesOrderByExpoOrd() {
        QnAType useTip = qnaTypeRepository.findByIdWithSubQnATypesOrderByExpoOrder(useTipId).orElse(null);

        assertNotNull(useTip);

        assertTrueUseTipSubQnATypeOrdering(useTip);
    }

    private void assertTrueUseTipSubQnATypeOrdering(QnAType useTip) {
        Iterator<QnAType> useTipSubTypes = useTip.getSubQnATypes().iterator();
        assertEquals(AUTHENTICATION_TITLE, useTipSubTypes.next().getTitle());
        assertEquals(CONNECTION_ERROR, useTipSubTypes.next().getTitle());
        assertEquals(SIGNUP_AND_CANCEL_TITLE, useTipSubTypes.next().getTitle());
    }
}
