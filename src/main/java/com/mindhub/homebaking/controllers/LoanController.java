package com.mindhub.homebaking.controllers;


import com.mindhub.homebaking.dto.LoanApplicationDTO;
import com.mindhub.homebaking.dto.LoanDTO;


import com.mindhub.homebaking.models.*;
import com.mindhub.homebaking.repositories.ClientLoanRepository;
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

import static com.mindhub.homebaking.models.RoleType.ADMIN;

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
    @Autowired
    private ClientLoanRepository clientLoanRepository;



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

//        Double amount = loanApplicationDTO.getAmount()*1.2;

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

        ClientLoan clientLoan = new ClientLoan(loan.getName(), loanApplicationDTO.getAmount(), loanApplicationDTO.getPayments());
        client.addClientLoan(clientLoan);
        loan.addClientLoan(clientLoan);

        loanService.saveClientLoan(clientLoan);


        return new ResponseEntity<>("Loan approved", HttpStatus.CREATED);


    }

    @PatchMapping ("/loans")
    public ResponseEntity<String> payLoan (Authentication authentication,
                                           @RequestParam Long Id,
                                           @RequestParam String number){

        Client client = clientService.getAuthenticatedClient(authentication);

        ClientLoan clientLoan = clientLoanRepository.findById(Id).orElse(null);
        Account account = accountService.findByNumber(number);
        double interest = clientLoan.getLoan().getInterest();

        Double paymentAmount = (clientLoan.getAmount() / clientLoan.getPayments()) * interest ;
        System.out.println(paymentAmount);

        if (client == null ){
            return new ResponseEntity<>("You're not allowed to do this", HttpStatus.FORBIDDEN);
        }

        if (account.getBalance() < paymentAmount){
            return new ResponseEntity<>(" insufficient funds", HttpStatus.FORBIDDEN);
        }

        clientLoan.setAmount(clientLoan.getAmount() - paymentAmount);
        clientLoan.setPayments(clientLoan.getPayments() - 1);
        clientLoanRepository.save(clientLoan);

        account.setBalance(account.getBalance() - paymentAmount);

        accountService.saveAccount(account);


        return new ResponseEntity<>("Loan payed", HttpStatus.CREATED);

    }


    @PostMapping ("/loans/create")
    public ResponseEntity<String> createLoan(Authentication authentication,
                                             @RequestParam String name,
                                             @RequestParam double maxAmount,
                                             @RequestParam List <Integer> payments,
                                             @RequestParam double interest
                                             ) {
        Client client = clientService.getAuthenticatedClient(authentication);

        if (client.getRole() != ADMIN){
            return new ResponseEntity<>("You're not allowed to do this", HttpStatus.FORBIDDEN);
        };

        Loan loan = new Loan(name, maxAmount, payments, interest);
        loanService.saveLoan(loan);

        return new ResponseEntity<>("Loan created", HttpStatus.CREATED);

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






