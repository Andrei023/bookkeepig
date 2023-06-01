package com.devplant.bookkeeping.repository;

import com.devplant.bookkeeping.model.Rule;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Component
public class RuleRepository {

    private static final List<Rule> rules = new ArrayList<>();

    public Rule fetchRuleById(String id) {
        return rules.stream()
                .filter(r -> r.getId().toString().equals(id))
                .findAny()
                .orElseThrow(() -> new NoSuchElementException("Rule with id " + id + " not found"));
    }

    public List<Rule> fetchRules() {
        return rules;
    }

    public Rule createRule(Rule rule) {

        rule.setId(UUID.randomUUID());
        rules.add(rule);
        return rule;
    }

    public Rule updateRule(Rule rule) {
        deleteRule(rule.getId().toString());
        rules.add(rule);
        return rule;
    }

    public void deleteRule(String id) {
        rules.removeIf(r -> r.getId().toString().equals(id));
    }

    public String checkRule(String receiverAccount, String senderAccount) {
        return rules.stream()
                .filter(rule -> rule.getReceiverAccount().equals(receiverAccount))
                .filter(rule -> rule.getSenderAccount().equals(senderAccount))
                .findAny()
                .map(rule -> "YES, when " + rule.getName().toLowerCase() + ".")
                .orElse("NO, no rule found.");
    }
}
