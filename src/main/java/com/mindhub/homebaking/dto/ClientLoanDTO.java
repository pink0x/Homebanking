package com.mindhub.homebaking.dto;

import com.mindhub.homebaking.models.Client;
import com.mindhub.homebaking.models.ClientLoan;


import java.util.stream.Collectors;

public class ClientLoanDTO {

    private Long Id;
    private Long loanId;

    private String name;

    private Double amount;

    private int payments;


    public ClientLoanDTO (ClientLoan clientLoan){
        Id= clientLoan.getId();
        name= clientLoan.getLoan().getName();
        amount = clientLoan.getAmount();
        payments = clientLoan.getPayments();
        loanId = clientLoan.getLoan().getId();



    }


    public Long getId() {
        return Id;
    }

    public Long getLoanId() {
        return loanId;
    }

    public String getName() {
        return name;
    }

    public Double getAmount() {
        return amount;
    }

    public int getPayments() {
        return payments;
    }
}
