package com.devplant.bookkeeping.repository;

import com.devplant.bookkeeping.model.Account;
import com.faunadb.client.errors.NotFoundException;
import jakarta.annotation.PostConstruct;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class AccountRepository {

    private static final String COMMA_DELIMITER = ",";

    private static final List<Account> accounts = new ArrayList<>();

    @PostConstruct
    void init() {
        loadAccounts();
    }

    public List<Account> fetchAccounts() {
        return accounts;
    }

    public Account fetchAccountById(String id) {
        return accounts.stream()
                .filter(a -> a.getId().equals(id))
                .findAny()
                .orElseThrow(() -> new NotFoundException("Account with id " + id + " not found"));
    }

    private void loadAccounts() {
        //ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        // is = classloader.getResourceAsStream("data/accounts.csv");
        try (BufferedReader br = new BufferedReader(
                new FileReader(this.getClass().getResource("data/accounts.csv").getFile()))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(COMMA_DELIMITER);
                accounts.add(Account.builder().id(values[0]).name(values[1]).build());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
