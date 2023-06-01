package com.devplant.bookkeeping.controller;

import com.devplant.bookkeeping.dto.request.CheckRuleRequest;
import com.devplant.bookkeeping.service.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RuleLogicController {

    @Autowired
    private RuleService ruleService;

    @PostMapping(value = "/check-rule")
    public String checkRule(@RequestBody CheckRuleRequest checkRuleRequest) throws Exception {
        return ruleService.checkRule(checkRuleRequest.getReceiverAccount(), checkRuleRequest.getSenderAccount());
    }
}
