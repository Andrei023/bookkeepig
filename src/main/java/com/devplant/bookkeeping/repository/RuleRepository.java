package com.devplant.bookkeeping.repository;

import com.devplant.bookkeeping.model.Rule;
import com.faunadb.client.errors.BadRequestException;
import com.faunadb.client.errors.NotFoundException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class RuleRepository {

    private static final List<Rule> rules = new ArrayList<>();

    public Rule fetchRuleById(String id) {
        return rules.stream()
                .filter(r -> r.getId().toString().equals(id))
                .findAny()
                .orElseThrow(() -> new NotFoundException("Rule with id " + id + " not found"));
    }

    public List<Rule> fetchRules() {
        return rules;
    }

    public Rule createRule(Rule rule) {

        rule.setId(UUID.randomUUID());
        rules.add(rule);
        return rule;
    }

    public Rule updateRule(Rule rule, String id) {
        Rule ruleToUpdate = rules.stream()
                .filter(r -> r.getId().toString().equals(id))
                .findAny()
                .orElseThrow(() -> new BadRequestException("Rule with id " + id + "does not exist"));
        if (StringUtils.isNotBlank(rule.getName())) {
            ruleToUpdate.setName(rule.getName());
        }
        if (StringUtils.isNotBlank(rule.getReceiverAccount())) {
            ruleToUpdate.setReceiverAccount(rule.getName());
        }
        if (StringUtils.isNotBlank(rule.getSenderAccount())) {
            ruleToUpdate.setSenderAccount(rule.getName());
        }
        deleteRule(ruleToUpdate.getId().toString());
        rules.add(ruleToUpdate);
        return ruleToUpdate;
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
