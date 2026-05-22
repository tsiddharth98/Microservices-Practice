package com.shunya.cards.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(
        name = "Cards",
        description = "Schema to hold card information"
)
public class CardsDto {

    @NotEmpty(message = "Mobile number cannot be null or empty!!")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be of 10 digits!!")
    @Schema(
            description = "Mobile number of a customer",
            example = "2963548726"
    )
    private String mobileNumber;

    @NotEmpty(message = "Card number cannot be null or empty!!")
    @Pattern(regexp = "(^$|[0-9]{12})", message = "Card number must be of 12 digits!!")
    @Schema(
            description = "Card number of a customer",
            example = "254312125867"
    )
    private String cardNumber;

    @NotEmpty(message = "Card type cannot be null or empty!!")
    @Schema(
            description = "Type of the Card",
            example = "Debit Card"
    )
    private String cardType;

    @Positive(message = "Total limit should be greater than zero!!")
    @Schema(
            description = "Total amount limit available against a Card",
            example = "1,00,000"
    )
    private int totalLimit;

    @PositiveOrZero(message = "Total amount used should be equal or greater than zero!!")
    @Schema(
            description = "Total amount used by a customer",
            example = "30,000"
    )
    private int amountUsed;

    @PositiveOrZero(message = "Total available amount should be equal or greater than zero!!")
    @Schema(
            description = "Total available amount against a Card",
            example = "70,000"
    )
    private int availableAmount;
}
