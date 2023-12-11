package com.mindhub.homebaking.dto;

import com.mindhub.homebaking.models.Client;

import java.util.List;
import java.util.stream.Collectors;

public class ClientDTO {

    private Long id;
    private String fullName;

    private List <AccountDTO> accounts;

    public ClientDTO (Client client){
        id= client.getId();
        fullName= client.getName() + " " + client.getLastName();
        accounts = client.getAccounts().stream().map(account -> new AccountDTO(account)).collect(Collectors.toList());

    }


    public Long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public List<AccountDTO> getAccounts() {
        return accounts;
    }
}
