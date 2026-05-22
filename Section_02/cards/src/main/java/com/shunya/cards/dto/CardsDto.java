package com.shunya.cards.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(
        name = "Card",
        description = "Schema to hold card information"
)
public class CardsDto {

    @NotEmpty(message = "Mobile number cannot be empty!!")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "mobile number must be of 10 digits!!")
    @Schema(
            description = "Mobile number registered to ApexBank Card",
            example = "2963548726"
    )
    private String mobileNumber;

    @NotEmpty(message = "Card number cannot be empty!!")
    @Schema(
            description = "Card number of ApexBank Card",
            example = "254312125867"
    )
    private String cardNumber;

    @NotEmpty(message = "Card type cannot be empty!!")
    @Schema(
            description = "Card type of ApexBank Card",
            example = "Debit Card"
    )
    private String cardType;

    @NotEmpty(message = "Total limit cannot be empty!!")
    @Schema(
            description = "Total limit of ApexBank Card",
            example = "1,00,000"
    )
    private Integer totalLimit;

    @NotEmpty(message = "Amount used cannot be empty!!")
    @Schema(
            description = "Amount used of ApexBank Card",
            example = "30,000"
    )
    private Integer amountUsed;

    @NotEmpty(message = "Available amount cannot be empty!!")
    @Schema(
            description = "Available amount in ApexBank Card",
            example = "70,000"
    )
    private Integer availableAmount;
}
