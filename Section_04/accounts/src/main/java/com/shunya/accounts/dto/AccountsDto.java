package com.shunya.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Schema(
        name = "Account",
        description = "Schema to hold account information"
)
@Data
public class AccountsDto {

    @NotEmpty(message = "Email address cannot be null or empty")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Account number must be of 10 digits")
    @Schema(
            description = "Account number of ApexBank account",
            example = "2543125867"
    )
    private Long accountNumber;

    @NotEmpty(message = "Account type cannot be null or empty")
    @Schema(
            description = "Account type of ApexBank account",
            example = "Savings"
    )
    private String accountType;

    @NotEmpty(message = "Branch address cannot be null or empty")
    @Schema(
            description = "ApexBank branch address",
            example = "123, main street, New York"
    )
    private String branchAddress;
}
