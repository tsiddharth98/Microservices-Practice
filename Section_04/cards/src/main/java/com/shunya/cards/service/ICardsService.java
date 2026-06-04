package com.shunya.cards.service;

import com.shunya.cards.dto.CardsDto;
import com.shunya.cards.dto.ResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface ICardsService {

    /**
     * @param mobileNumber - Mobile Number of the Customer
     * */
    void createCard(String mobileNumber);

    /**
     * @param mobileNumber -  Input mobile Number
     * @return Card Details based on a given mobileNumber
     * */
    CardsDto fetchAllCards(String mobileNumber);

    /**
     * @param cardsDto - card dto object
     * @return a boolean indicating if the card update is successful or not
     * */
    boolean updateCard(CardsDto cardsDto);

    /**
     * @param mobileNumber - Input Mobile Number
     * @return a boolean indicating if the delete of card details is successful or not
     * */
    boolean deleteCard(String mobileNumber);
}
