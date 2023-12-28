package com.mindhub.homebaking.controllers;

import com.mindhub.homebaking.models.*;
import com.mindhub.homebaking.repositories.CardRepository;
import com.mindhub.homebaking.repositories.ClientRepository;
;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Set;

@RestController
@RequestMapping("/api/clients/current/cards")
public class CardController {

    @Autowired
    CardRepository cardRepository;

    @Autowired
    ClientRepository clientRepository;


    @PostMapping
    public ResponseEntity<Object> createCard(@RequestParam CardType cardType,
                                             @RequestParam CardColor cardColor,
                                             Authentication authentication) {
        Client client = clientRepository.findByEmail(authentication.getName());


        long color = client.getClientCards().stream()
                .filter(card -> card.getCardColor() == cardColor)
                .count();

        long type = client.getClientCards().stream()
                .filter(card -> card.getCardType() == cardType)
                .count();

        long typeColor = client.getClientCards().stream()
                .filter(card -> card.getCardColor().equals(cardColor) && card.getCardType().equals(cardType))
                .count();


        if (client.getClientCards().size() >= 6) {
            return new ResponseEntity<>("You reached the maximun amount of cards", HttpStatus.FORBIDDEN);
        }

        if (type >= 3) {
            return new ResponseEntity<>("You can't have more than 3" + type + "cards", HttpStatus.FORBIDDEN);
        }

        if (color >= 2) {
            return new ResponseEntity<>("you can't have more than 2" + color + "cards", HttpStatus.FORBIDDEN);
        }

        if (typeColor >= 1) {
            return new ResponseEntity<>("You already have a " + color + type + "card", HttpStatus.FORBIDDEN);
        }


        int cvv = (int) (Math.random() * 999 + 100);

        String cardNumber = generateRandomCardNumber();

        String cardholder = client.getName() + " " + client.getLastName();

        LocalDate startDate = LocalDate.now();

        LocalDate expirationDate = startDate.plusYears(5);

        Card card = new Card(cardColor, cardType, cardNumber, cvv, startDate, expirationDate);

        client.addCard(card);
        cardRepository.save(card);

        return new ResponseEntity<>("Card created for the client", HttpStatus.CREATED);

    }
    private String generateRandomCardNumber() {
        StringBuilder cardNumber = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            int section = (int) (Math.random() * 9000 + 1000);
            cardNumber.append(section).append("-");
        }
        return cardNumber.substring(0, cardNumber.length() - 1);
    }
}