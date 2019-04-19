package com.example.cs.springbootcsmanagement.bootstrap;

import com.example.cs.springbootcsmanagement.domains.PoC;
import com.example.cs.springbootcsmanagement.domains.PoCQnAType;
import com.example.cs.springbootcsmanagement.domains.QnAType;
import com.example.cs.springbootcsmanagement.enums.PoCType;
import com.example.cs.springbootcsmanagement.repositories.PoCQnATypeRepository;
import com.example.cs.springbootcsmanagement.repositories.PoCRepository;
import com.example.cs.springbootcsmanagement.repositories.QnATypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import java.util.Arrays;

//@Component
//@Slf4j
//@Profile(value = {"local"})
public class DataLoader implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private PoCRepository pocRepository;

    @Autowired
    private QnATypeRepository qnaTypeRepository;

    @Autowired
    private PoCQnATypeRepository pocQnATypeRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        cleanDatas();
        loadDatas();
    }

    private void cleanDatas() {
        pocQnATypeRepository.deleteAll();
        pocRepository.deleteAll();
        qnaTypeRepository.deleteAll();
    }

    private void loadDatas() {
        PoC osc = new PoC(PoCType.OSC);
        PoC osb = new PoC(PoCType.OSB);
        pocRepository.saveAll(Arrays.asList(osc, osb));

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

        PoCQnAType oscConnectionError = PoCQnAType.builder().poc(osc).qnaType(connectionError).expoOrder(1).build();
        PoCQnAType oscAuthentication = PoCQnAType.builder().poc(osc).qnaType(authentication).expoOrder(2).build();
        PoCQnAType oscMembership = PoCQnAType.builder().poc(osc).qnaType(membership).expoOrder(3).build();
        pocQnATypeRepository.saveAll(Arrays.asList(oscConnectionError, oscAuthentication, oscMembership));

        PoCQnAType oscPurchaseHistory = PoCQnAType.builder().poc(osc).qnaType(purchaseHistory).expoOrder(2).build();
        PoCQnAType oscPurchaseCancel = PoCQnAType.builder().poc(osc).qnaType(purchaseCancel).expoOrder(1).build();
        pocQnATypeRepository.saveAll(Arrays.asList(oscPurchaseHistory, oscPurchaseCancel));

        PoCQnAType oscDeviceSupport = PoCQnAType.builder().poc(osc).qnaType(deviceSupport).expoOrder(1).build();
        pocQnATypeRepository.save(oscDeviceSupport);

        PoCQnAType osbConnectionError = PoCQnAType.builder().poc(osb).qnaType(connectionError).expoOrder(1).build();
        PoCQnAType osbAuthentication = PoCQnAType.builder().poc(osb).qnaType(authentication).expoOrder(2).build();
        pocQnATypeRepository.saveAll(Arrays.asList(osbConnectionError, osbAuthentication));

        PoCQnAType osbPurchaseCancel = PoCQnAType.builder().poc(osb).qnaType(purchaseCancel).expoOrder(1).build();
        pocQnATypeRepository.save(osbPurchaseCancel);

        PoCQnAType osbDeviceSupport = PoCQnAType.builder().poc(osb).qnaType(deviceSupport).expoOrder(1).build();
        pocQnATypeRepository.save(osbDeviceSupport);
    }
}
