package com.mindhub.homebaking.controllers;

import com.mindhub.homebaking.dto.AccountDTO;
import com.mindhub.homebaking.dto.ClientDTO;
import com.mindhub.homebaking.models.*;
import com.mindhub.homebaking.repositories.AccountRepository;

import com.mindhub.homebaking.repositories.CardRepository;
import com.mindhub.homebaking.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;



@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private CardRepository cardRepository;

    @RequestMapping("/{id}")
    public AccountDTO getOneAccount(@PathVariable Long id) {
        return new AccountDTO(accountRepository.findById(id).orElse(null));
    }


    @PostMapping
    public ResponseEntity<String> createAccount(Authentication authentication) {


        Client client = clientRepository.findByEmail(authentication.getName());

        String number;

        do {
            number = "VIN" + getRandomNumber(001, 99999999);

        } while (accountRepository.existsByNumber(number));

        Account account = new Account(number, 0, LocalDate.now());
        client.addAccount(account);
        accountRepository.save(account);

        return new ResponseEntity<>("Cuenta creada con exito", HttpStatus.CREATED);


    }

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

}
