package com.mindhub.homebaking.dto;

import com.mindhub.homebaking.models.Transaction;
import com.mindhub.homebaking.models.TransactionType;

import java.time.LocalDate;

public class TransactionDTO {

    private TransactionType type;
    private double amount;
    private String description;
    private LocalDate date;

    private Double currentBalance;


    public TransactionDTO(Transaction transaction) {
        type = transaction.getType();
        amount = transaction.getAmount();
        description = transaction.getDescription();
        date= transaction.getDate();
        currentBalance = transaction.getCurrentBalance();
    }




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

    public Double getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(Double currentBalance) {
        this.currentBalance = currentBalance;
    }
}
