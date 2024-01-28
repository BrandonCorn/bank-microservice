package com.bkbytes.cards.service.impl;

import com.bkbytes.cards.constants.CardConstants;
import com.bkbytes.cards.dto.CardsDto;
import com.bkbytes.cards.dto.ResponseDto;
import com.bkbytes.cards.entity.Cards;
import com.bkbytes.cards.exception.CardAlreadyExistsException;
import com.bkbytes.cards.exception.ResourceNotFoundException;
import com.bkbytes.cards.mapper.CardsMapper;
import com.bkbytes.cards.repository.CardsRepository;
import com.bkbytes.cards.service.ICardsService;
import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class CardsServiceImpl implements ICardsService {

  private CardsRepository cardsRepository;
  @Override
  public void createCard(String mobileNumber) {
    Optional<Cards> optionalCard = cardsRepository.findByMobileNumber(mobileNumber);
    if (optionalCard.isPresent()){
      throw new CardAlreadyExistsException(("This card already exists ")+mobileNumber);
    }
    else{
      cardsRepository.save(createNewCard(mobileNumber));
    }
  }

  @Override
  public CardsDto fetchCard(String mobileNumber) {
    Cards card = cardsRepository.findByMobileNumber(mobileNumber).orElseThrow(
            () -> new ResourceNotFoundException(
                    "Card",
                    "mobileNumber",
                    mobileNumber

    )
    );
      return CardsMapper.mapToCardsDto(card, new CardsDto());
  }

  @Override
  public boolean updateCard(CardsDto cardsDto) {
    Cards card = cardsRepository.findByCardNumber(cardsDto.getCardNumber()).orElseThrow(
            () -> new ResourceNotFoundException("Cards", "cardNumber", cardsDto.getCardNumber())
    );
    CardsMapper.mapToCards(cardsDto, card);
    cardsRepository.save(card);
    return true;
  }

  @Override
  public boolean deleteCard(String mobileNumber) {
    Cards card = cardsRepository.findByMobileNumber(mobileNumber).orElseThrow(
            () -> new ResourceNotFoundException("Cards", "mobileNumber", mobileNumber)
    );
    cardsRepository.delete(card);
    return true;
  }

  private Cards createNewCard(String mobileNumber){
    Cards card = new Cards();
    long randomAccountNumber = 100000000000L + new Random().nextInt(900000000);
    card.setMobileNumber(mobileNumber);
    card.setCardNumber(Long.toString(randomAccountNumber));
    card.setCardType(CardConstants.CREDIT_CARD);
    card.setTotalLimit(CardConstants.NEW_CARD_LIMIT);
    card.setAvailableAmount(CardConstants.NEW_CARD_LIMIT);
    card.setAmountUsed(0);
    return card;
  }
}
