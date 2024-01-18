package com.mindhub.homebaking.dto;

import com.mindhub.homebaking.models.Account;
import com.mindhub.homebaking.models.Client;
import com.mindhub.homebaking.models.Transaction;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AccountDTO {
    private Long id;

    private Double balance;
    private String number;
    private LocalDate date;

    private Boolean status = true;

    private List<TransactionDTO> transactions = new ArrayList<>();

    public AccountDTO (Account account){
        id = account.getId();
        balance = account.getBalance();
        number = account.getNumber();
        date = account.getDate();
        transactions = account.getTransactions().stream().filter(accountDTO-> account.getStatus()).map(transaction -> new TransactionDTO(transaction)).collect(Collectors.toList());

    }




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<TransactionDTO> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<TransactionDTO> transactions) {
        this.transactions = transactions;
    }

    public  Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
