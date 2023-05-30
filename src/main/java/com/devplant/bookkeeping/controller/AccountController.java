package com.devplant.bookkeeping.controller;

import com.devplant.bookkeeping.model.Account;
import com.devplant.bookkeeping.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/accounts")
    public List<Account> getAccounts() {
        return accountService.getAccounts();
    }

    @GetMapping("/accounts/{accountId}")
    public Account getRuleById(@PathVariable String accountId) {
        return accountService.getAccountById(accountId);
    }
}
