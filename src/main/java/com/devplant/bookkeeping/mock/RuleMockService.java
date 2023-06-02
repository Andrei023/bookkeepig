package com.devplant.bookkeeping.mock;

import com.devplant.bookkeeping.model.Rule;
import com.devplant.bookkeeping.repository.RuleRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RuleMockService {

    @Autowired
    private RuleRepository ruleRepository;

    @Value("${mock.rules}")
    private Boolean mockRules;

    @PostConstruct
    void init() {
        if (Boolean.TRUE.equals(mockRules)) {
            loadMockRules();
        }
    }

    private void loadMockRules() {
        ruleRepository.createRule(Rule.builder()
                .receiverAccount("371")
                .senderAccount("401")
                .name("registering acquired goods from a supplier")
                .build());
        ruleRepository.createRule(Rule.builder()
                .receiverAccount("371")
                .senderAccount("408")
                .name("registering acquired goods from a supplier but the invoice has not yet been received")
                .build());
        ruleRepository.createRule(Rule.builder()
                .receiverAccount("371")
                .senderAccount("4511")
                .name("registering goods acquired from affiliated entities")
                .build());
        ruleRepository.createRule(Rule.builder()
                .receiverAccount("371")
                .senderAccount("456")
                .name("registering goods received from the company owners")
                .build());
        ruleRepository.createRule(Rule.builder()
                .receiverAccount("607")
                .senderAccount("371")
                .name("goods are sold")
                .build());
        ruleRepository.createRule(Rule.builder()
                .receiverAccount("6582")
                .senderAccount("371")
                .name("goods are donated")
                .build());
        ruleRepository.createRule(Rule.builder()
                .receiverAccount("671")
                .senderAccount("371")
                .name("goods perish due to disasters or extraordinary events")
                .build());
    }
}
