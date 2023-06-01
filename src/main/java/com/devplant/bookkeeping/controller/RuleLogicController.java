package com.devplant.bookkeeping.controller;

import com.devplant.bookkeeping.dto.request.CheckRuleRequest;
import com.devplant.bookkeeping.service.RuleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RuleLogicController {

    @Autowired
    private RuleService ruleService;

    @PostMapping(value = "/check-rule", consumes = {"text/plain"})
    public String checkRule(@RequestBody String checkRuleRequest) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        CheckRuleRequest checkRuleRequestObj = mapper.readValue(checkRuleRequest, CheckRuleRequest.class);
        return ruleService.checkRule(checkRuleRequestObj.getReceiverAccount(), checkRuleRequestObj.getSenderAccount());
    }
}
