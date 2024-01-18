package com.mindhub.homebaking.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private double maxAccount;
    private double interest;

    @ElementCollection
    private List<Integer> payments = new ArrayList<>();

//    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
//    @JoinColumn(name= "client")
//    private List <Client> clients = new ArrayList<>();

    @OneToMany (mappedBy = "loan" ,fetch = FetchType.EAGER)
    private Set<ClientLoan> clientLoans = new HashSet<>();

    public Loan() {
    }

    public Loan(String name, double maxAccount, List<Integer> payments, double interest) {
        this.name = name;
        this.maxAccount = maxAccount;
        this.payments = payments;
        this.interest = interest;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMaxAccount() {
        return maxAccount;
    }

    public void setMaxAccount(double maxAccount) {
        this.maxAccount = maxAccount;
    }

//    public List<Client> getClients() {
//        return clients;
//    }
//
//    public void setClients(List<Client> clients) {
//        this.clients = clients;
//    }

    public List<Integer> getPayments() {
        return payments;
    }

    public void setPayments(List<Integer> payments) {
        this.payments = payments;
    }

    public Set<ClientLoan> getClientLoans() {
        return clientLoans;
    }

    public void setClientLoans(Set<ClientLoan> clientLoans) {
        this.clientLoans = clientLoans;
    }

    public void addClientLoan(ClientLoan clientLoan){
        clientLoan.setLoan(this);
        this.clientLoans.add(clientLoan);

    }

    public double getInterest() {
        return interest;
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }

    public Long getId() {
        return id;
    }
}



