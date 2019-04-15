package com.example.cs.springbootcsmanagement.bootstrap;

import com.example.cs.springbootcsmanagement.domains.PoC;
import com.example.cs.springbootcsmanagement.enums.PoCType;
import com.example.cs.springbootcsmanagement.repositories.PoCRepository;
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

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if (pocRepository.count() == 0) {
            log.debug("Loading PoCs.");

            loadPocs();
        }
    }

    private void loadPocs() {
        pocRepository.saveAll(Arrays.asList(new PoC(PoCType.OSC), new PoC(PoCType.OSB)));
    }
}
