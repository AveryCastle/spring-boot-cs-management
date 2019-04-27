package com.example.cs.springbootcsmanagement.repositories;

import com.example.cs.springbootcsmanagement.domains.QnAType;
import com.example.cs.springbootcsmanagement.enums.PoCType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
    public void test() {
        System.out.println("test start ==>");
        List<QnAType> entityGraph = pocQnATypeRepository.findByPoCIdEntityGraph(PoCType.OSC.getLegacyCode());

        System.out.println("test end ==>");
        Iterator<QnAType> iterator = entityGraph.iterator();
        while (iterator.hasNext()) {
            QnAType qnaType = iterator.next();
            System.out.println("id = " + qnaType.getId() + ", title: " + qnaType.getTitle());
        }
    }
}
