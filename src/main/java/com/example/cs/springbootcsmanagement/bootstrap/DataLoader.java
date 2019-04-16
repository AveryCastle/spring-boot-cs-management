package com.example.cs.springbootcsmanagement.bootstrap;

import com.example.cs.springbootcsmanagement.domains.PoC;
import com.example.cs.springbootcsmanagement.domains.QnAType;
import com.example.cs.springbootcsmanagement.enums.PoCType;
import com.example.cs.springbootcsmanagement.repositories.PoCRepository;
import com.example.cs.springbootcsmanagement.repositories.QnATypeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Slf4j
@Profile(value = {"local"})
public class DataLoader implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private PoCRepository pocRepository;

    @Autowired
    private QnATypeRepository qnaTypeRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if (pocRepository.count() == 0) {
            log.debug("Loading PoCs.");

            loadPocs();
        }

        if (qnaTypeRepository.count() == 0) {
            log.debug("Loading QnATypes.");

            loadQnATypes();
        }
    }

    private void loadPocs() {
        pocRepository.saveAll(Arrays.asList(new PoC(PoCType.OSC), new PoC(PoCType.OSB)));
    }

    private void loadQnATypes() {
        QnAType useTip = QnAType.builder().title("이용문의").expoOrder(3).build();
        QnAType purchase = QnAType.builder().title("구매/결제").expoOrder(2).build();
        QnAType device = QnAType.builder().title("단말").expoOrder(1).build();
        qnaTypeRepository.saveAll(Arrays.asList(useTip, purchase, device));

        QnAType membership = QnAType.builder().title("회원 가입/탈퇴").expoOrder(3).upperQnAType(useTip).build();
        QnAType authentication = QnAType.builder().title("회원인증").expoOrder(2).upperQnAType(useTip).build();
        QnAType connectionError = QnAType.builder().title("접속 실패/오류").expoOrder(1).upperQnAType(useTip).build();
        qnaTypeRepository.saveAll(Arrays.asList(membership, authentication, connectionError));

        QnAType purchaseHistory = QnAType.builder().title("구매내역").expoOrder(2).upperQnAType(purchase).build();
        QnAType purchaseCancel = QnAType.builder().title("구매/취소문의").expoOrder(1).upperQnAType(purchase).build();
        qnaTypeRepository.saveAll(Arrays.asList(purchaseHistory, purchaseCancel));

        QnAType deviceSupport = QnAType.builder().title("단말지원").expoOrder(1).upperQnAType(device).build();
        qnaTypeRepository.saveAll(Arrays.asList(deviceSupport));
    }
}
