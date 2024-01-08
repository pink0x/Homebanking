package com.mindhub.homebaking.dto;

import com.mindhub.homebaking.models.Loan;

import java.util.List;

public class LoanDTO {
    private Long Id;

    private String name;

    private List<Integer> payments;

    private Double amount;


    public LoanDTO(Loan loan) {
        Id = loan.getId() ;
        this.name = loan.getName();
        this.payments = loan.getPayments();
        this.amount = loan.getMaxAccount();
    }


    public Long getId() {
        return Id;
    }

    public String getName() {
        return name;
    }

    public List<Integer> getPayments() {
        return payments;
    }

    public Double getAmount() {
        return amount;
    }
}
