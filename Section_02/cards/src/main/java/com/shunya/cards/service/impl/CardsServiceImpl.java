package com.shunya.cards.service.impl;

import static com.shunya.cards.constants.CardsConstants.*;
import com.shunya.cards.dto.CardsDto;
import com.shunya.cards.entity.Cards;
import com.shunya.cards.exception.CardAlreadyExistsException;
import com.shunya.cards.exception.ResourceNotFoundException;
import com.shunya.cards.mapper.CardsMapper;
import com.shunya.cards.repository.ICardsRepository;
import com.shunya.cards.service.ICardsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@AllArgsConstructor
public class CardsServiceImpl implements ICardsService {

    private ICardsRepository cardsRepository;

    /**
     * @param mobileNumber - Mobile Number of the Customer
     *
     */
    @Override
    public void createCard(String mobileNumber) {

        cardsRepository.findByMobileNumber(mobileNumber)
                .ifPresent((cards -> {
                    throw new CardAlreadyExistsException("Card already exist with given mobile number: "+mobileNumber);
                }));
        cardsRepository.save(createNewCard(mobileNumber));
    }

    /**
     * @param mobileNumber - Mobile Number of the Customer
     * @return the new card details
     */
    private Cards createNewCard(String mobileNumber) {
        Cards newCard = new Cards();
        long randomCardNumber = 100000000000L + new Random().nextInt(900000000);
        newCard.setCardNumber(Long.toString(randomCardNumber));
        newCard.setMobileNumber(mobileNumber);
        newCard.setCardType(CREDIT_CARD);
        newCard.setTotalLimit(NEW_CARD_LIMIT);
        newCard.setAmountUsed(0);
        newCard.setAvailableAmount(NEW_CARD_LIMIT);
        return newCard;
    }

    /**
     * @param mobileNumber - mobile number registered for card
     *
     */
    @Override
    public CardsDto fetchAllCards(String mobileNumber) {
        Cards cards = cardsRepository
                .findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Card", "Mobile Number", mobileNumber));

        return CardsMapper.mapToCardsDto(cards, new CardsDto());
    }

    /**
     * @param cardsDto - card dto object
     * @return a boolean indicating if the card update is successful or not
     * */
    @Override
    public boolean updateCard(CardsDto cardsDto) {
        Cards card = cardsRepository.findByCardNumber(cardsDto.getCardNumber())
                .orElseThrow(() -> new ResourceNotFoundException("Card", "Card Number", cardsDto.getCardNumber()));

        CardsMapper.mapToCards(cardsDto, card);
        cardsRepository.save(card);
        return true;
    }

    /**
     * @param mobileNumber - mobile number registered for card
     * @return a boolean indicating if the delete of card is successful or not
     *
     */
    @Override
    public boolean deleteCard(String mobileNumber) {
        Cards card = cardsRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Card", "Mobile Number", mobileNumber));

        cardsRepository.deleteById(card.getCardId());
        return true;
    }
}
