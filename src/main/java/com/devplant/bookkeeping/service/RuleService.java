package com.devplant.bookkeeping.service;

import com.devplant.bookkeeping.model.Rule;
import com.devplant.bookkeeping.repository.RuleRepository;
import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class RuleService {

    private final RuleRepository ruleRepository;

    private final AccountService accountService;

    private static final String YES = "YES";

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
        validateCreateRule(rule);
        return ruleRepository.createRule(rule);
    }

    public Rule updateRule(Rule rule, String id) {
        Rule entityToUpdate = validateUpdateRule(rule, id);
        return ruleRepository.updateRule(entityToUpdate, id);
    }

    public void deleteRule(String id) {
        validateRule(id);
        ruleRepository.deleteRule(id);
    }

    public String checkRule(String receiverAccount, String senderAccount) {
        validateCheckRule(receiverAccount, senderAccount);
        return ruleRepository.checkRule(receiverAccount, senderAccount);
    }

    private void validateCreateRule(Rule rule) {
        validateAccount(rule.getReceiverAccount(), "receiverAccount");
        validateAccount(rule.getSenderAccount(), "senderAccount");
        validateName(rule.getName());
        validateSameAccounts(rule.getReceiverAccount(), rule.getSenderAccount());
        validateExistingAccounts(rule.getReceiverAccount(), rule.getSenderAccount());
    }

    private Rule validateUpdateRule(Rule rule, String id) {
        Rule ruleEntity = null;
        boolean accountChanged = false;
        try {
            ruleEntity = (Rule) validateRule(id).clone();
        } catch (Exception e) {
        }
        if (StringUtils.isNotBlank(rule.getName())) {
            validateName(rule.getName());
            ruleEntity.setName(rule.getName());
        }
        if (StringUtils.isNotBlank(rule.getReceiverAccount())) {
            validateAccount(rule.getReceiverAccount(), "receiverAccount");
            accountChanged = true;
            ruleEntity.setReceiverAccount(rule.getReceiverAccount());
        }
        if (StringUtils.isNotBlank(rule.getSenderAccount())) {
            validateAccount(rule.getSenderAccount(), "senderAccount");
            accountChanged = true;
            ruleEntity.setSenderAccount(rule.getSenderAccount());
        }
        if (accountChanged) {
            validateSameAccounts(ruleEntity.getReceiverAccount(), ruleEntity.getSenderAccount());
            validateExistingAccounts(ruleEntity.getReceiverAccount(), ruleEntity.getSenderAccount());
        }
        return ruleEntity;
    }

    private void validateCheckRule(String receiverAccount, String senderAccount) {
        validateAccount(receiverAccount, "receiverAccount");
        validateAccount(senderAccount, "senderAccount");
        validateSameAccounts(receiverAccount, senderAccount);
    }

    private void validateName(String name) {
        if (StringUtils.isBlank(name)) {
            throw new IllegalArgumentException("Name should not be blank");
        }
    }

    private Rule validateRule(String id) {
        return ruleRepository.fetchRuleById(id);
    }

    private void validateAccount(String accountId, String type) {
        if (StringUtils.isBlank(accountId)) {
            throw new IllegalArgumentException("Account \'" + type + "\' should not be blank");
        }
        if (accountService.getAccountById(accountId) == null) {
            throw new NoSuchElementException("Account with ID" + accountId + " does not exist");
        }
    }

    private void validateSameAccounts(String receiverAccount, String senderAccount) {
        if (receiverAccount.equals(senderAccount)) {
            throw new IllegalStateException("Cannot have the same account for receiver and sender");
        }
    }

    private void validateExistingAccounts(String receiverAccount, String senderAccount) {
        if (ruleRepository.checkRule(receiverAccount, senderAccount).contains(YES)) {
            throw new IllegalStateException("The same combination of receiverAccount and senderAccount already exists on a rule");
        }
    }
}