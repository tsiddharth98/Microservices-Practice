package com.shunya.cards.service;

import com.shunya.cards.dto.CardsDto;
import com.shunya.cards.dto.ResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface ICardsService {

    /**
    * @param mobileNumber - mobile number registered for card
    * */
    public void createCard(String mobileNumber);

    /**
     * @param mobileNumber - mobile number registered for card
     * */
    public CardsDto fetchAllCards(String mobileNumber);

    /**
     * @param cardsDto - card dto object
     * @return a boolean indicating if the card update is successful or not
     * */
    public boolean updateCard(CardsDto cardsDto);

    /**
     * @param mobileNumber - mobile number registered for card
     * @return a boolean indicating if the delete of card is successful or not
     * */
    public boolean deleteCard(String mobileNumber);
}
