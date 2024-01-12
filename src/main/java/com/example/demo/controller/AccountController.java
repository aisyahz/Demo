package com.example.demo.controller;

import com.example.demo.exception.ResourceNotFound;
import com.example.demo.model.Account;
import com.example.demo.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;

    @GetMapping
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    // Build create account REST API
    @PostMapping
    public Account createAccount(@RequestBody Account account) {
        return accountRepository.save(account);
    }

    @GetMapping("{id}")
    // Build get account by id REST API
    public ResponseEntity<Account> getAccountById(@PathVariable long id) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new ResourceNotFound("Account not exist with id:" + id));
        return ResponseEntity.ok(account);
    }

    // Build update account REST API
    @PutMapping("{id}")
    public ResponseEntity<Account> updateAccount(@PathVariable long id, @RequestBody Account accountDetails) {
        Account updateAccount = accountRepository.findById(id).orElseThrow(() -> new ResourceNotFound("Account not exist with id: " + id));

        // Update account details as needed
        updateAccount.setAccountNumber(accountDetails.getAccountNumber());
        updateAccount.setAccountBalance(accountDetails.getAccountBalance());

        accountRepository.save(updateAccount);
        return ResponseEntity.ok(updateAccount);
    }

    // Build delete account REST API
    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteAccount(@PathVariable long id) {

        Account account = accountRepository.findById(id).orElseThrow(() -> new ResourceNotFound("Account not exist with id: " + id));

        accountRepository.delete(account);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
