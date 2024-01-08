package com.mindhub.homebaking.dto;

import com.mindhub.homebaking.models.Account;
import com.mindhub.homebaking.models.Client;
import com.mindhub.homebaking.models.Loan;
import jakarta.persistence.ManyToOne;

import java.util.ArrayList;
import java.util.List;

public class LoanApplicationDTO {

    private Long Id;


    private String AccountNumber;

    private double amount;

    private Integer payments;





    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getAccountNumber() {
        return AccountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        AccountNumber = accountNumber;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Integer getPayments() {
        return payments;
    }

    public void setPayments(Integer payments) {
        this.payments = payments;
    }
}
