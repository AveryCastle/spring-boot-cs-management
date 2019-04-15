package com.example.cs.springbootcsmanagement.repositories;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.example.cs.springbootcsmanagement.domains.QnAType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
class QnATypeRepositoryITest extends BaseRepositoryTest {

    @Autowired
    private QnATypeRepository qnaTypeRepository;

    List<QnAType> upperQnATypes;

    @BeforeEach
    public void setUp() {
        upperQnATypes = createUpperQnATypes();

        qnaTypeRepository.saveAll(upperQnATypes);
    }

    @AfterEach
    public void tearDown() {
        qnaTypeRepository.deleteAll();
    }

    @Test
    public void givenQnATypes_whenFindAllByUpperQnAType_thenFetchAllEntityGraphOrderByExpoOrd() {
        List<QnAType> qnaTypes = qnaTypeRepository.findAllWithSubQnATypesOrderByExpoOrder();

        assertEquals(3, qnaTypes.size());

        QnAType device = qnaTypes.get(0);
        QnAType payment = qnaTypes.get(1);
        QnAType useTip = qnaTypes.get(2);

        assertEquals(DEVICE_TITLE, device.getTitle());
        assertEquals(PAYMENT_TITLE, payment.getTitle());
        assertEquals(USE_TIP_TITLE, useTip.getTitle());

        List<String> actualTitles = useTip.getSubQnATypes().stream()
                .map(qnaType -> qnaType.getTitle())
                .collect(Collectors.toList());
        String[] expectedTitle = {CONNECTION_ERROR, AUTHENTICATION_TITLE, SIGNUP_AND_CANCEL_TITLE};
        assertArrayEquals(expectedTitle, actualTitles.toArray(String[]::new));
    }

    @Test
    public void givenQnATypes_whenFindByUpperQnAType_thenFetchSubQnATypesOrderByExpoOrd() {
        QnAType savedUseTip = upperQnATypes.get(0);
        QnAType useTip = qnaTypeRepository.findByIdWithSubQnATypesOrderByExpoOrder(savedUseTip.getId()).orElse(null);

        assertNotNull(useTip);

        List<String> actualTitles = useTip.getSubQnATypes().stream()
                .map(qnaType -> qnaType.getTitle())
                .collect(Collectors.toList());
        String[] expectedTitle = {CONNECTION_ERROR, AUTHENTICATION_TITLE, SIGNUP_AND_CANCEL_TITLE};
        assertArrayEquals(expectedTitle, actualTitles.toArray(String[]::new));
    }
}
