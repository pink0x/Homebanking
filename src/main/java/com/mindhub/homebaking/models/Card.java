package com.mindhub.homebaking.models;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private CardColor cardColor;
    private CardType cardType;
    private String number;
    private String cardHolder;
    private Integer cvv;
    private LocalDate fromDate;
    private LocalDate thruDate;

    @ManyToOne
    @JoinColumn(name = "client")
    private Client client;

    public Card(CardColor cardColor, CardType cardType, String number, String cardHolder, Integer cvv, LocalDate fromDate, LocalDate thruDate) {
        this.cardColor = cardColor;
        this.cardType = cardType;
        this.number = number;
        this.cardHolder = cardHolder;
        this.cvv = cvv;
        this.fromDate = fromDate;
        this.thruDate = thruDate;
    }

    public Card() {
    }

    //-------------GETTERS Y SETTERS-------------------------

    public CardColor getCardColor() {
        return cardColor;
    }

    public void setCardColor(CardColor cardColor) {
        this.cardColor = cardColor;
    }

    public CardType getCardType() {
        return cardType;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public void setCardHolder(String cardHolder) {
        this.cardHolder = cardHolder;
    }

    public Integer getCvv() {
        return cvv;
    }

    public void setCvv(Integer cvv) {
        this.cvv = cvv;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDate getThruDate() {
        return thruDate;
    }

    public void setThruDate(LocalDate thruDate) {
        this.thruDate = thruDate;
    }

    public Long getId() {
        return id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
