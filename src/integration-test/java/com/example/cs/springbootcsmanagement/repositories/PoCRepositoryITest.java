package com.example.cs.springbootcsmanagement.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.cs.springbootcsmanagement.domains.PoC;
import com.example.cs.springbootcsmanagement.dto.QnATypeDTO;
import com.example.cs.springbootcsmanagement.enums.PoCType;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class PoCRepositoryITest {

    @Autowired
    private PoCRepository pocRepository;

    @Test
    public void shouldSave() {
        PoC saved = pocRepository.save(new PoC(PoCType.OSB));

        assertEquals(PoCType.OSB.getLegacyCode(), saved.getId());
        assertEquals(PoCType.OSB.getDescription(), saved.getName());
    }

    @Test
    public void test() {
        List<QnATypeDTO> list = pocRepository.findWithSubTypesOrderByExpoOrder(PoCType.OSC.getLegacyCode());
        System.out.println(list);
    }
}
