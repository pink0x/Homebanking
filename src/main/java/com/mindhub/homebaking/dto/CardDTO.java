package com.mindhub.homebaking.dto;

import com.mindhub.homebaking.models.Card;
import com.mindhub.homebaking.models.CardColor;
import com.mindhub.homebaking.models.CardType;
import com.mindhub.homebaking.models.Client;

import java.time.LocalDate;

public class CardDTO {
    private Long id;

    private CardColor cardColor;
    private CardType cardType;
    private String number;
    private String cardHolder;
    private Integer cvv;
    private LocalDate fromDate;
    private LocalDate thruDate;





    public CardDTO(Card card) {
        this.id = card.getId();
        this.cardColor = card.getCardColor();
        this.cardType = card.getCardType();
        this.number = card.getNumber();
        this.cardHolder = card.getClient().getName() + " " + card.getClient().getLastName();
        this.cvv = card.getCvv();
        this.fromDate = card.getFromDate();
        this.thruDate = card.getThruDate();
    }







    public Long getId() {
        return id;
    }

    public CardColor getCardColor() {
        return cardColor;
    }

    public CardType getCardType() {
        return cardType;
    }

    public String getNumber() {
        return number;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public Integer getCvv() {
        return cvv;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public LocalDate getThruDate() {
        return thruDate;
    }
}


