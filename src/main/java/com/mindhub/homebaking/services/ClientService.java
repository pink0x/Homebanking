package com.mindhub.homebaking.services;

import com.mindhub.homebaking.models.Client;
import org.springframework.security.core.Authentication;

public interface ClientService {
    Client getAuthenticatedClient (Authentication authentication);
}
