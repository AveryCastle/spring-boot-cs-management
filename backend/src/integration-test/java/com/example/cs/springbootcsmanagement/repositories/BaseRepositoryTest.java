package com.example.cs.springbootcsmanagement.repositories;

import com.example.cs.springbootcsmanagement.domains.QnAType;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public abstract class BaseRepositoryTest {

    protected static final String USE_TIP_TITLE = "이용문의";
    protected static final String PAYMENT_TITLE = "구매/결제";
    protected static final String DEVICE_TITLE = "단말";

    protected static final String SIGNUP_AND_CANCEL_TITLE = "회원 가입/탈퇴";
    protected static final String AUTHENTICATION_TITLE = "회원인증";
    protected static final String CONNECTION_ERROR = "접속 실패/오류";

    protected static final String PURCHASE_HISTORY_TITLE = "구매내역";
    protected static final String PURCHASE_AND_CANCEL_TITLE = "구매/취소문의";

    protected static final String DEVICE_SUPPORT_TITLE = "단말지원";

    protected List<QnAType> createUpperQnATypes() {
        List<QnAType> qnaTypes = new ArrayList<>();

        final Map<String, String[]> qnaMap = new LinkedHashMap<>();
        qnaMap.put(USE_TIP_TITLE, new String[]{SIGNUP_AND_CANCEL_TITLE, AUTHENTICATION_TITLE, CONNECTION_ERROR});
        qnaMap.put(PAYMENT_TITLE, new String[]{PURCHASE_HISTORY_TITLE, PURCHASE_AND_CANCEL_TITLE});
        qnaMap.put(DEVICE_TITLE, new String[]{DEVICE_SUPPORT_TITLE});

        int index = 3;
        Iterator<String> iterator = qnaMap.keySet().iterator();
        while (iterator.hasNext()) {
            String title = iterator.next();
            QnAType upperQnAType = QnAType.builder().title(title).expoOrder(index--).build();
            createSubQnATypes(upperQnAType, qnaMap.get(title));
            qnaTypes.add(upperQnAType);
        }

        return qnaTypes;
    }

    protected List<QnAType> createSubQnATypes(QnAType upperQnAType, String[] subQnATypeTitle) {
        List<QnAType> qnaTypes = new ArrayList<>();

        int length = subQnATypeTitle.length;
        for (int index = 0; index < length; index++) {
            QnAType subQnAType = QnAType.builder()
                    .title(subQnATypeTitle[index])
                    .upperQnAType(upperQnAType)
                    .expoOrder(length - index)
                    .build();
            qnaTypes.add(subQnAType);
        }

        return qnaTypes;
    }
}
