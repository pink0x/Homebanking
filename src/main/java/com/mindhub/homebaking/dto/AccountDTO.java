package com.mindhub.homebaking.dto;

import com.mindhub.homebaking.models.Account;
import com.mindhub.homebaking.models.Client;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;

public class AccountDTO {
    private Long id;

    private Double balance;
    private String number;
    private LocalDate date;

    public AccountDTO (Account account){
        id = account.getId();
        balance = account.getBalance();
        number = account.getNumber();
        date = account.getDate();
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
}
