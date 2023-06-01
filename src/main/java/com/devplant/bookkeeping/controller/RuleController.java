package com.devplant.bookkeeping.controller;

import com.devplant.bookkeeping.model.Rule;
import com.devplant.bookkeeping.service.RuleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RuleController {

    @Autowired
    private RuleService ruleService;

    @GetMapping("/rules/{ruleId}")
    public Rule getRuleById(@PathVariable String ruleId) {
        return ruleService.getRuleById(ruleId);
    }

    @GetMapping("/rules")
    public List<Rule> getRules() {
        return ruleService.getRules();
    }

    @PostMapping(value = "/rules", consumes = {"text/plain"})
    public Rule createRule(@RequestBody String rule) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Rule ruleObj = mapper.readValue(rule, Rule.class);
        return ruleService.createRule(ruleObj);
    }

    @PutMapping(value = "/rules/{ruleId}", consumes = {"text/plain"})
    public Rule updateRule(@RequestBody String rule, @PathVariable String ruleId) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Rule ruleObj = mapper.readValue(rule, Rule.class);
        return ruleService.updateRule(ruleObj, ruleId);
    }

    @DeleteMapping("/rules/{ruleId}")
    public void deleteRule(@PathVariable String ruleId) {
        ruleService.deleteRule(ruleId);
    }
}
