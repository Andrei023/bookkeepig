package com.devplant.bookkeeping.service;

import com.devplant.bookkeeping.model.Account;
import com.devplant.bookkeeping.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {
    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<Account> getAccounts() {
        return accountRepository.fetchAccounts();
    }

    public Account getAccountById(String accountId) {
        return accountRepository.fetchAccountById(accountId);
    }
}
