package com.shunya.cards.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter @Setter
public class Cards extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cardId;

    private String mobileNumber;
    private String cardNumber;
    private String cardType;
    private Integer totalLimit;
    private Integer amountUsed;
    private Integer availableAmount;

}
