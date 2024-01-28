package com.bkbytes.cards.mapper;

import com.bkbytes.cards.dto.CardsDto;
import com.bkbytes.cards.entity.Cards;

public class CardsMapper {

  public static Cards mapToCards(CardsDto cardDto,Cards card){
    card.setMobileNumber(cardDto.getMobileNumber());
    card.setCardNumber(cardDto.getCardNumber());
    card.setCardType(cardDto.getCardType());
    card.setAmountUsed(cardDto.getAmountUsed());
    card.setTotalLimit(cardDto.getTotalLimit());
    card.setAvailableAmount(cardDto.getAvailableAmount());
    return card;
  }

  public static CardsDto mapToCardsDto(Cards card, CardsDto cardsDto){
    cardsDto.setMobileNumber(card.getMobileNumber());
    cardsDto.setCardType(card.getCardType());
    cardsDto.setCardNumber(card.getCardNumber());
    cardsDto.setTotalLimit(card.getTotalLimit());
    cardsDto.setAmountUsed(card.getAmountUsed());
    cardsDto.setAvailableAmount(card.getAvailableAmount());
    return cardsDto;
  }
}
