package com.mindhub.homebaking.controllers;

import com.mindhub.homebaking.dto.AccountDTO;
import com.mindhub.homebaking.dto.ClientDTO;
import com.mindhub.homebaking.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/accounts/")
public class AccountController {
    @Autowired
    private AccountRepository accountRepository;
    @RequestMapping("/{id}")
    public AccountDTO getOneAccount (@PathVariable Long id){
        return new AccountDTO(accountRepository.findById(id).orElse(null));
    }
}
