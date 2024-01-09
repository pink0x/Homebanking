package com.mindhub.homebaking.services.impl;

import com.mindhub.homebaking.models.Client;
import com.mindhub.homebaking.repositories.ClientRepository;
import com.mindhub.homebaking.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
   private ClientRepository clientRepository;


    @Override
    public Client getAuthenticatedClient(Authentication authentication) {
        return clientRepository.findByEmail(authentication.getName());
    }
}
