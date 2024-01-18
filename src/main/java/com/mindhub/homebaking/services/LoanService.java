package com.mindhub.homebaking.services;

import com.mindhub.homebaking.dto.LoanApplicationDTO;
import com.mindhub.homebaking.dto.LoanDTO;
import com.mindhub.homebaking.models.ClientLoan;
import com.mindhub.homebaking.models.Loan;
import com.mindhub.homebaking.repositories.LoanRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

public interface LoanService {


  void saveClientLoan(ClientLoan clientLoan);

  void saveLoan(Loan loan);
   Loan findById(Long Id);

    List<LoanDTO> getLoans ();

    public ResponseEntity<String> getLoanApplication (String email, LoanApplicationDTO loanApplicationDTO);
}

