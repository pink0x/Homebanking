package com.mindhub.homebaking.models;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double balance;
    private String number;
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "client")
    private Client client;


    public Account() {
    }

    public Account(String number, double balance, LocalDate date) {
        this.number = number;
        this.balance = balance;
        this.date = date;
    }




//----------------------GETTERS AND SETTERS----------------------
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
