package com.example.cs.springbootcsmanagement.repositories;

import com.example.cs.springbootcsmanagement.domains.QnAType;
import com.example.cs.springbootcsmanagement.enums.PoCType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.Iterator;
import java.util.List;

@SpringBootTest
class PoCQnATypeRepositoryTest {

    @Autowired
    private PoCRepository pocRepository;

    @Autowired
    private PoCQnATypeRepository pocQnATypeRepository;

    @Autowired
    private QnATypeRepository qnaTypeRepository;

    @Test
    @Transactional
    public void test() {
        List<QnAType> entityGraph = pocQnATypeRepository.findByPoCIdEntityGraph(PoCType.OSC.getLegacyCode());
        Iterator<QnAType> iterator = entityGraph.iterator();
        while (iterator.hasNext()) {
            QnAType qnaType = iterator.next();
            System.out.println(qnaType.toString());
        }
    }
}
