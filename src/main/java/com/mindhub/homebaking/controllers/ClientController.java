package com.mindhub.homebaking.controllers;


import com.mindhub.homebaking.dto.ClientDTO;
import com.mindhub.homebaking.models.Client;
import com.mindhub.homebaking.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    @RequestMapping("/all")
    public List<ClientDTO> getAllClient(){
        return clientRepository.findAll()
                .stream()
                .map(client -> new ClientDTO(client))
                .collect(Collectors.toList());

    }

    @RequestMapping("/{id}")
    public ClientDTO getOneClient (@PathVariable Long id){
        return new ClientDTO(clientRepository.findById(id).orElse(null));
    }


}
