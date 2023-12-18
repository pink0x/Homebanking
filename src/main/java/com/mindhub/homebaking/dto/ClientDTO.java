package com.mindhub.homebaking.dto;

import com.mindhub.homebaking.models.Card;
import com.mindhub.homebaking.models.Client;
import com.mindhub.homebaking.models.ClientLoan;
import com.mindhub.homebaking.models.Loan;

import java.util.List;
import java.util.stream.Collectors;

public class ClientDTO {

    private Long id;
    private String name;
    private String lastName;

    private String email;

    private List <AccountDTO> accounts;

    private List <ClientLoanDTO> loans;

    private List<CardDTO> cards;




    public ClientDTO (Client client){
        id= client.getId();
        name = client.getName();
        lastName = client.getLastName();
        email= client.getEmail();
        accounts = client.getAccounts().stream().map(account -> new AccountDTO(account)).collect(Collectors.toList());
        loans = client.getClientLoans().stream().map(clientLoan -> new ClientLoanDTO(clientLoan)).collect(Collectors.toList());
        cards = client.getClientCards().stream().map(card -> new CardDTO(card)).collect(Collectors.toList());


    }


    public Long getId() {
        return id;
    }


    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public List<AccountDTO> getAccounts() {
        return accounts;
    }



    public List<ClientLoanDTO> getLoans() {
        return loans;
    }


    public List<CardDTO> getCards() {
        return cards;
    }
}
