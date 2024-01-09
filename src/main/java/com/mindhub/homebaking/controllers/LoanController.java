package com.mindhub.homebaking.controllers;


import com.mindhub.homebaking.dto.LoanApplicationDTO;
import com.mindhub.homebaking.dto.LoanDTO;


import com.mindhub.homebaking.models.*;
import com.mindhub.homebaking.services.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @Autowired
    private ClientService clientService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private TransactionService transactionService;



    @GetMapping("/loans")
    public List<LoanDTO> getLoans() {
        return loanService.getLoans();

    }

    @PostMapping("/loans")
    @Transactional
    public ResponseEntity<String> getLoanApplication(Authentication authentication,
                                                     @RequestBody LoanApplicationDTO loanApplicationDTO) {



        Account account = accountService.findByNumber(loanApplicationDTO.getAccountNumber());
        Loan loan = loanService.findById(loanApplicationDTO.getId());
        Client client = clientService.getAuthenticatedClient(authentication);

        Double amount = loanApplicationDTO.getAmount()*1.2;

        if (loanApplicationDTO.getAmount() > loan.getMaxAccount()) {
            return new ResponseEntity<>("You've reached the max amount available", HttpStatus.FORBIDDEN);
        }


        if (account == null) {
            return new ResponseEntity<>("The account doesn't exists", HttpStatus.FORBIDDEN);
        }

        if (!loan.getPayments().contains(loanApplicationDTO.getPayments())) {
            return new ResponseEntity<>("Payments not available", HttpStatus.FORBIDDEN);
        }

        if (loanApplicationDTO.getAmount() < 0 ||
                loanApplicationDTO.getPayments() == null ||
                loanApplicationDTO.getId() == null ||
                loanApplicationDTO.getAccountNumber() == null) {
            return new ResponseEntity<>("Something's missing", HttpStatus.FORBIDDEN);
        }



        Transaction transactionLoan = new Transaction(TransactionType.CREDIT, loanApplicationDTO.getAmount(), "loan approved", LocalDate.now());
        account.setBalance(account.getBalance() + loanApplicationDTO.getAmount());
        account.addTransaction(transactionLoan);
        transactionService.saveTransaction(transactionLoan);

        ClientLoan clientLoan = new ClientLoan(loan.getName(), amount, loanApplicationDTO.getPayments());
        client.addClientLoan(clientLoan);
        loan.addClientLoan(clientLoan);

        loanService.saveClientLoan(clientLoan);


        return new ResponseEntity<>("Loan approved", HttpStatus.CREATED);


    }
}

//    public ResponseEntity<String> getLoanApplication (Authentication authentication,
//                                                      @RequestBody LoanApplicationDTO loanApplicationDTO) {
//
//        Client client = clientRepository.findByEmail(authentication.getName());
//        Account account = accountRepository.findByNumber(loanApplicationDTO.getAccountNumber());
//        Loan loan = loanRepository.getById(loanApplicationDTO.getId());
//
//
//
//        Double amount = loanApplicationDTO.getAmount()*1.2;
//
//
//
//        if (loanApplicationDTO.getAmount() > loan.getMaxAccount()){
//            return new ResponseEntity<>("You've reached the max amount available", HttpStatus.FORBIDDEN);
//        };
//
//        if (account == null){
//            return new ResponseEntity<>("The account doesn't exists", HttpStatus.FORBIDDEN);
//        }
//
//        if (!loan.getPayments().contains(loanApplicationDTO.getPayments())){
//            return new ResponseEntity<>("Payments not available", HttpStatus.FORBIDDEN);
//        }
//
//        if (loanApplicationDTO.getAmount() < 0 ||
//                loanApplicationDTO.getPayments() ==null ||
//                loanApplicationDTO.getId() ==null ||
//                loanApplicationDTO.getAccountNumber() == null){
//            return new ResponseEntity<>("Something's missing", HttpStatus.FORBIDDEN);
//        }
//
//
//
//        Transaction transactionLoan = new Transaction(TransactionType.CREDIT, loanApplicationDTO.getAmount(), "loan approved", LocalDate.now());
//        account.setBalance(account.getBalance()+ loanApplicationDTO.getAmount());
//        account.addTransaction(transactionLoan);
//        transactionRepository.save(transactionLoan);
//
//        ClientLoan clientLoan = new ClientLoan(loan.getName(),amount,loanApplicationDTO.getPayments());
//        client.addClientLoan(clientLoan);
//        loan.addClientLoan(clientLoan);
//
//        clientLoanRepository.save(clientLoan);
//
//
//
//        return new ResponseEntity<>("Loan approved", HttpStatus.CREATED);
//
//
//    }






