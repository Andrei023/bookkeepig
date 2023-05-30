package com.devplant.bookkeeping.service;

import com.devplant.bookkeeping.model.Rule;
import com.devplant.bookkeeping.repository.RuleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class RuleService {

    private final RuleRepository ruleRepository;

    private final AccountService accountService;

    public RuleService(RuleRepository ruleRepository, AccountService accountService) {
        this.ruleRepository = ruleRepository;
        this.accountService = accountService;
    }

    public Rule getRuleById(String ruleId) {
        return ruleRepository.fetchRuleById(ruleId);
    }

    public List<Rule> getRules() {
        return ruleRepository.fetchRules();
    }

    public Rule createRule(Rule rule) {
        validateAccount(rule.getReceiverAccount());
        validateAccount(rule.getSenderAccount());
        if (rule.getReceiverAccount().equals(rule.getSenderAccount())) {
            throw new IllegalStateException("Cannot have same receiver and sender");
        }
        return ruleRepository.createRule(rule);
    }

    public Rule updateRule(Rule rule, String id) {
        return ruleRepository.updateRule(rule, id);
    }

    public void deleteRule(String id) {
        ruleRepository.deleteRule(id);
    }

    public String checkRule(String receiverAccount, String senderAccount) {
        return ruleRepository.checkRule(receiverAccount, senderAccount);
    }

    private void validateAccount(String accountId) {
        if (accountService.getAccountById(accountId) == null) {
            throw new NoSuchElementException("Account with ID" + accountId + " does not exist");
        }
    }
}