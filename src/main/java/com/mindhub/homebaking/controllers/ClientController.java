package com.mindhub.homebaking.controllers;


import com.mindhub.homebaking.dto.ClientDTO;
import com.mindhub.homebaking.models.*;
import com.mindhub.homebaking.repositories.AccountRepository;
import com.mindhub.homebaking.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    public PasswordEncoder passwordEncoder;

    @RequestMapping("/all")
    public List<ClientDTO> getAllClient(){
        return clientRepository.findAll()
                .stream()
                .map(client -> new ClientDTO(client))
                .collect(Collectors.toList());
    }
    @RequestMapping("/clients/current")
    public ResponseEntity<ClientDTO> getOneClient(Authentication authentication) {
        Client client = clientRepository.findByEmail(authentication.getName());
        return new ResponseEntity<>(new ClientDTO(client), HttpStatus.OK);
    }

//    @RequestMapping("/{id}")
//    public ClientDTO getOneClient (@PathVariable Long id){
//        return new ClientDTO(clientRepository.findById(id).orElse(null));
//    }



    @PostMapping("/clients")
    public ResponseEntity<Object> createClient ( @RequestParam String firstName,
                                                 @RequestParam String lastName,
                                                 @RequestParam String email,
                                                 @RequestParam String password){

        if(firstName.isBlank()){
            return new ResponseEntity<>("Please, enter a First Name",HttpStatus.FORBIDDEN);
        }
        if(lastName.isBlank()){
            return new ResponseEntity<>("Please, enter a Last Name",HttpStatus.FORBIDDEN);
        }
        if(email.isBlank()){
            return new ResponseEntity<>("Please, enter an email",HttpStatus.FORBIDDEN);
        }
        if(password.isBlank()){
            return new ResponseEntity<>("Please, enter a Password",HttpStatus.FORBIDDEN);
        }

        if (clientRepository.existsByEmail(email) ){
            return new ResponseEntity<>("The email already exists", HttpStatus.FORBIDDEN);
        }



     Client client = new Client (firstName,lastName,email, passwordEncoder.encode(password), RoleType.CLIENT);

     clientRepository.save(client);


        String number;

        do {
            number = "VIN" + getRandomNumber(10000000, 99999999);

        }while(accountRepository.existsByNumber(number));

        Account account = new Account(number,0, LocalDate.now());
        client.addAccount(account);
        accountRepository.save(account);

     return new ResponseEntity<>("Registrado con exito", HttpStatus.CREATED);

    }

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }




}
