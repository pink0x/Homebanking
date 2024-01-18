package com.mindhub.homebaking;

import com.mindhub.homebaking.models.Account;
import com.mindhub.homebaking.models.Loan;
import com.mindhub.homebaking.models.Transaction;
import com.mindhub.homebaking.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.MatcherAssert.assertThat;

import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;


@DataJpaTest

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

public class RepositoryTest {


    @Autowired
    ClientRepository clientRepository;
    @Autowired
    CardRepository cardRepository;
    @Autowired
    TransactionRepository transactionRepository;

    //------------------LOAN REPOSITORY TEST---------------------------

    @Autowired
    LoanRepository loanRepository;

    @Test
    public void existLoans() {
        List<Loan> loans = loanRepository.findAll();
        assertThat(loans,is(not(empty())));
    }


    @Test
    public void existPersonalLoan() {
        List<Loan> loans = loanRepository.findAll();
        assertThat(loans, hasItem(hasProperty("name", is("Personal"))));
    }

    @Test
    public void existHipotecarioLoan() {
        List<Loan> loans = loanRepository.findAll();
        assertThat(loans, hasItem(hasProperty("name", is("Hipotecario"))));
    }

    @Test
    public void existAutomotrizLoan() {
        List<Loan> loans = loanRepository.findAll();
        assertThat(loans, hasItem(hasProperty("name", is("automotriz"))));
    }

//-----------------------------ACCOUNT REPOSITORY TEST-----------------------------

    @Autowired
    AccountRepository accountRepository;

//  @Test
//    public void AccountNumberTest (){
//      List  <Account> accounts= accountRepository.findAll();
//      assertThat(accounts, (actualValue, greaterThan(5)) ;

//      assertThat(actualValue, greaterThan(5));

  }


//}


