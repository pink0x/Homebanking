package com.mindhub.homebaking.controllers;

import com.mindhub.homebaking.models.*;
import com.mindhub.homebaking.repositories.AccountRepository;
import com.mindhub.homebaking.repositories.ClientRepository;
import com.mindhub.homebaking.repositories.LoanRepository;
import com.mindhub.homebaking.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

import static com.mindhub.homebaking.models.TransactionType.CREDIT;
import static com.mindhub.homebaking.models.TransactionType.DEBIT;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    ClientRepository clientRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    TransactionRepository transactionRepository;

    @Transactional
    @PostMapping
    public ResponseEntity<Object> createTransaction(@RequestParam String accountNumberOrigin,
                                                    @RequestParam Double amount,
                                                    @RequestParam String accountNumberDestiny,
                                                    @RequestParam String description,


                                             Authentication authentication){

        Account accountOrigin = accountRepository.findByNumber(accountNumberOrigin);
        Account accountDestiny = accountRepository.findByNumber(accountNumberDestiny);

        Transaction transactionDebit = new Transaction(DEBIT , amount, description, LocalDate.now(),accountOrigin.getBalance() - amount);
        Transaction transactionCredit = new Transaction(CREDIT, amount, description, LocalDate.now(),accountDestiny.getBalance() + amount);




        accountOrigin.addTransaction(transactionDebit);
        accountDestiny.addTransaction(transactionCredit);


        accountOrigin.setBalance(accountOrigin.getBalance() - amount);
        accountDestiny.setBalance(accountDestiny.getBalance() + amount);

//        Double currentBalanceCredit = transactionCredit.getCurrentBalance();
//        Double currentBalanceDebit  = transactionDebit.getCurrentBalance();
//
//        if (transactionCredit.getType() == DEBIT){
//            currentBalanceDebit = accountOrigin.getBalance() - amount;
//        }
//        if (transactionCredit.getType() == CREDIT){
//            currentBalanceCredit = accountOrigin.getBalance() + amount;
//        }


        transactionRepository.save(transactionCredit);
        transactionRepository.save(transactionDebit);





        if (accountDestiny == null){
            return new ResponseEntity<>("The account doesn't exist", HttpStatus.NOT_FOUND);
        }

        if (accountOrigin == null){
            return new ResponseEntity<>("The account doesn't exist", HttpStatus.NOT_FOUND);
        }

        if (accountOrigin.getBalance() < amount){
            return new ResponseEntity<>("Insufficient funds", HttpStatus.FORBIDDEN);
        }
        if (accountOrigin == accountDestiny){
            return new ResponseEntity<>("You are trying to make a translation to the origin account", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>("The trasaction was succesfully completed ", HttpStatus.CREATED);

    }

}
