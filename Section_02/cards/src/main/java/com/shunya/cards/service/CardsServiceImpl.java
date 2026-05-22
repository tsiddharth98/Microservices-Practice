package com.shunya.cards.service;

import static com.shunya.cards.constants.CardsConstants.*;
import com.shunya.cards.dto.CardsDto;
import com.shunya.cards.entity.Cards;
import com.shunya.cards.exception.CardAlreadyExistsException;
import com.shunya.cards.exception.ResourceNotFoundException;
import com.shunya.cards.mapper.CardsMapper;
import com.shunya.cards.repository.ICardsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@AllArgsConstructor
public class CardsServiceImpl implements ICardsService {

    private ICardsRepository cardsRepository;

    /**
     * @param mobileNumber - mobile number registered for card
     *
     */
    @Override
    public void createCard(String mobileNumber) {

        cardsRepository.findByMobileNumber(mobileNumber)
                .ifPresent((cards -> {
                    throw new CardAlreadyExistsException("Card already exist with given mobile number: "+mobileNumber);
                }));

        Cards card = new Cards();
        card.setMobileNumber(mobileNumber);
        long randomCardNumber = 100000000000L + new Random().nextInt(900000000);
        card.setCardNumber(String.valueOf(randomCardNumber));
        card.setCardType(DEBIT);
        card.setTotalLimit(100000);
        card.setAmountUsed(0);
        card.setAvailableAmount(100000);

        cardsRepository.save(card);
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
     *
     */
    @Override
    public boolean updateCard(CardsDto cardsDto) {
        Cards card = cardsRepository.findByMobileNumber(cardsDto.getMobileNumber())
                .orElseThrow(() -> new ResourceNotFoundException("Card", "Mobile Number", cardsDto.getMobileNumber()));

        card.setCardType(cardsDto.getCardType());
        card.setCardNumber(cardsDto.getCardNumber());
        card.setMobileNumber(cardsDto.getMobileNumber());
        card.setTotalLimit(cardsDto.getTotalLimit());
        card.setAmountUsed(cardsDto.getAmountUsed());
        card.setAvailableAmount(cardsDto.getAvailableAmount());

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

        cardsRepository.deleteByMobileNumber(mobileNumber);
        return true;
    }
}
