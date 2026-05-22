package com.shunya.cards.controller;

import static com.shunya.cards.constants.CardsConstants.*;

import com.shunya.cards.dto.CardsDto;
import com.shunya.cards.dto.ErrorResponseDto;
import com.shunya.cards.dto.ResponseDto;
import com.shunya.cards.service.ICardsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
@AllArgsConstructor
@Validated
@Tag(
        name = "CRUD REST APIs for Cards in ApexBank",
        description = "CRUD REST APIs in ApexBank to CREATE, UPDATE, FETCH and DELETE cards details"
)
public class CardsController {

    private ICardsService iCardsService;

    @PostMapping("/create")
    @Operation(
            summary = "Create cards REST API",
            description = "REST API to create a new card inside ApexBank"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Http status CREATED"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Http status INTERNAL SERVER ERROR",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    public ResponseEntity<ResponseDto> createCard(@Valid @RequestParam String mobileNumber) {
        iCardsService.createCard(mobileNumber);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(STATUS_201, MESSAGE_200));
    }

    @GetMapping("/fetch")
    @Operation(
            summary = "Fetch cards REST API",
            description = "REST API to fetch card inside ApexBank's account"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Http status CREATED"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Http status INTERNAL SERVER ERROR",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    public ResponseEntity<CardsDto> fetchAllCards(@Valid @RequestParam String mobileNumber) {
        CardsDto cardsDto = iCardsService.fetchAllCards(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(cardsDto);
    }

    @PutMapping("/update")
    @Operation(
            summary = "Update card REST API",
            description = "REST API to update a card inside ApexBank"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Http status CREATED"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Http status EXPECTATION_FAILED"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Http status INTERNAL SERVER ERROR",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    public ResponseEntity<ResponseDto> updateCard(@Valid @RequestBody CardsDto cardsDto) {
        boolean isUpdated = iCardsService.updateCard(cardsDto);
        if (isUpdated)
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(STATUS_200, MESSAGE_200));
        else
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseDto(STATUS_417, MESSAGE_417_UPDATE));
    }

    @DeleteMapping("/delete")
    @Operation(
            summary = "Delete cards REST API",
            description = "REST API to delete a card inside ApexBank"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Http status CREATED"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Http status EXPECTATION_FAILED"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Http status INTERNAL SERVER ERROR",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    public ResponseEntity<ResponseDto> deleteCard(@Valid @RequestParam String mobileNumber) {

        boolean isDeleted = iCardsService.deleteCard(mobileNumber);
        if (isDeleted)
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(STATUS_200, MESSAGE_200));
        else
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseDto(STATUS_417, MESSAGE_417_UPDATE));
    }
}
