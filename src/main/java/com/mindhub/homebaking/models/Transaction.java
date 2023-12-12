package com.mindhub.homebaking.models;

import jakarta.persistence.*;

import java.time.LocalDate;
@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;

 private TransactionType type;
 private double amount;
 private String description;
 private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "account")
    private Account account;


    public Transaction() {
    }

    public Transaction(TransactionType type, double amount, String description, LocalDate date) {
        this.type = type;
        this.amount = amount;
        this.description = description;
        this.date = date;
    }




    //------------------------- GETTERS Y SETTERS--------------------------
    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
