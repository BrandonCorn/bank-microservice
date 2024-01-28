package com.bkbytes.cards.service;

import com.bkbytes.cards.dto.CardsDto;

public interface ICardsService {

  void createCard(String mobileNumber);

  CardsDto fetchCard(String mobileNumber);

  boolean updateCard(CardsDto cardsDto);

  boolean deleteCard(String mobileNumber);
}
