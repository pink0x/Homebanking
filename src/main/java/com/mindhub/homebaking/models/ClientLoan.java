package com.mindhub.homebaking.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity

public class ClientLoan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String name;

    private Double amount;

    private int payments;


    @ManyToOne
    private Client client;
    @ManyToOne
    private Loan loan;


    public ClientLoan() {
    }

    public ClientLoan(String name, Double amount, int payments) {
        this.name = name;
        this.amount = amount;
        this.payments = payments;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Loan getLoan() {
        return loan;
    }

    public void setLoan(Loan loan) {
        this.loan = loan;

         }

    public Long getId() {
        return Id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPayments() {
        return payments;
    }

    public void setPayments(int payments) {
        this.payments = payments;
    }
}
