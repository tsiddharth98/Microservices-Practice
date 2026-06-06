package com.shunya.accounts.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Schema(
        name = "Customer",
        description = "Schema to hold Customer and Account information"
)
@Data
@JsonPropertyOrder({ "name", "email", "mobileNumber", "accountsDto" })
public class CustomerDto {

    @Schema(
            description = "Name of the Customer",
            example = "Ashish Sharma"
    )
    @NotEmpty(message = "Name cannot be null or empty")
    @Size(min = 3, max = 30, message = "The length of the customer name should be between 3 and 30")
    private String name;

    @Schema(
            description = "Email of the Customer",
            example = "ashishsharma@gmail.com"
    )
    @NotEmpty(message = "Email address cannot be null or empty")
    @Email(message = "Enter a valid email address")
    private String email;

    @Schema(
            description = "Mobile number of a Customer",
            example = "9456055159"
    )
    @NotEmpty(message = "Mobile number cannot be null or empty")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be of 10 digits")
    private String mobileNumber;

    @Schema(
            description = "Account details of a Customer"
    )
    private AccountsDto accountsDto;
}
