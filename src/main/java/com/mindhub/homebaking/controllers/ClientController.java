package com.mindhub.homebaking.controllers;


import com.mindhub.homebaking.dto.ClientDTO;
import com.mindhub.homebaking.models.Client;
import com.mindhub.homebaking.models.RoleType;
import com.mindhub.homebaking.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    public PasswordEncoder passwordEncoder;

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

    @RequestMapping("/current")
    public ResponseEntity<ClientDTO> getOneClient(Authentication authentication) {
        Client client = clientRepository.findByEmail(authentication.getName());
        return new ResponseEntity<>(new ClientDTO(client), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<String> createClient ( @RequestParam String firstName,
                                                 @RequestParam String lastName,
                                                 @RequestParam String email,
                                                 @RequestParam String password){

        if(firstName.isBlank()){
            return new ResponseEntity<>("Debe ingresar un nombre",HttpStatus.FORBIDDEN);
        }
        if(lastName.isBlank()){
            return new ResponseEntity<>("Debe ingresar un apellido",HttpStatus.FORBIDDEN);
        }
        if(email.isBlank()){
            return new ResponseEntity<>("Debe ingresar un email",HttpStatus.FORBIDDEN);
        }
        if(password.isBlank()){
            return new ResponseEntity<>("Debe ingresar una contraseña",HttpStatus.FORBIDDEN);
        }

        if (clientRepository.existByEmail(email) ){
            return new ResponseEntity<>("El email ya esta en uso", HttpStatus.FORBIDDEN);
        }



     Client client = new Client (firstName,lastName,email, passwordEncoder.encode(password), RoleType.CLIENT);

     clientRepository.save(client);

     return new ResponseEntity<>("Registrado con exito", HttpStatus.CREATED);

    }




}
