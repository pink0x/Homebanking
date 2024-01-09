package com.mindhub.homebaking.services.impl;

import com.mindhub.homebaking.dto.LoanApplicationDTO;
import com.mindhub.homebaking.dto.LoanDTO;
import com.mindhub.homebaking.models.*;
import com.mindhub.homebaking.repositories.*;
import com.mindhub.homebaking.services.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoanServiceImpl implements LoanService {

    @Autowired
     private LoanRepository loanRepository;
    @Autowired
    private ClientLoanRepository clientLoanRepository;

    @Autowired
    private TransactionRepository transactionRepository;




    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    ClientRepository clientRepository;


    @Override
    public Loan findById(Long Id) {
        return loanRepository.findById(Id).orElse(null);
    }

    @Override
    public List<LoanDTO> getLoans() {
        return  loanRepository.findAll().stream().map(LoanDTO::new).collect(Collectors.toList());
    }

    @Override
    public void saveClientLoan (ClientLoan clientLoan){
        clientLoanRepository.save(clientLoan);
    }

    @Override
    public ResponseEntity<String> getLoanApplication(String email, LoanApplicationDTO loanApplicationDTO) {
        return null;
    }


}
