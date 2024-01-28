package com.bkbytes.cards.controller;

import com.bkbytes.cards.constants.CardConstants;
import com.bkbytes.cards.dto.CardsDto;
import com.bkbytes.cards.dto.ResponseDto;
import com.bkbytes.cards.service.ICardsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "Cards REST API",
        description = "CRUD operations for Cards REST API"
)
@RestController
@RequestMapping(path="/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Validated
public class CardsController {

  private ICardsService iCardsService;
  @Operation(
          summary = "Create Card REST API",
          description = "Request to create Card"
  )
  @ApiResponses({
          @ApiResponse(
                  responseCode = CardConstants.STATUS_201,
                  description = "Http status CREATED"
          ),
          @ApiResponse(
                  responseCode = "500",
                  description = "Http Status INTERNAL SERVER ERROR"
          )
  })
  @PostMapping(path = "/create")
  public ResponseEntity<ResponseDto> createCard(@Valid @RequestParam @Pattern(regexp = "$|[0-9]{10}") String mobileNumber){
    iCardsService.createCard(mobileNumber);
    return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(new ResponseDto(CardConstants.STATUS_201, CardConstants.MESSAGE_201));
  }

  @Operation(
          summary = "Fetch Card REST API",
          description = "Request to fetch Card"
  )
  @ApiResponses({
          @ApiResponse(
                  responseCode = CardConstants.STATUS_200,
                  description = "Http Status FOUND"
          ),
          @ApiResponse(
                  responseCode = "404",
                  description = "Http Status NOT FOUND"
          )
  })
  @GetMapping(path="/fetch")
  public ResponseEntity<CardsDto> fetchCard(@Valid @RequestParam @Pattern(regexp = "$|[0-9]{10}")  String mobileNumber){
    CardsDto cardDto = iCardsService.fetchCard(mobileNumber);
    return ResponseEntity
            .status(HttpStatus.OK)
            .body(cardDto);
  }

  @Operation(
          summary = "Update Card REST API",
          description = "Request to update Card"
  )
  @ApiResponses({
          @ApiResponse(
                  responseCode = "200",
                  description = "Http Status OK"
          ),
          @ApiResponse(
                  responseCode = "417",
                  description = "Http Status failed to update"
          )
  })
  @PutMapping(path="/update")
  public ResponseEntity<ResponseDto> updateCard(@RequestBody CardsDto cardsDto){
    boolean isUpdated = iCardsService.updateCard(cardsDto);
    if(isUpdated){
      return ResponseEntity
              .status(HttpStatus.OK)
              .body(new ResponseDto(CardConstants.STATUS_200, CardConstants.MESSAGE_200));
    }
    else{
      return ResponseEntity
              .status(HttpStatus.EXPECTATION_FAILED)
              .body(new ResponseDto(CardConstants.STATUS_417, CardConstants.MESSAGE_417_UPDATE));
    }
  }

  @Operation(
          summary = "Delete Card REST APi",
          description = "Request to delete Card"
  )
  @ApiResponses({
          @ApiResponse(
                  responseCode = "200",
                  description = "Http Status OK"
          ),
          @ApiResponse(
                  responseCode = "417",
                  description = "Http Status failed to delete"
          )
  })
  @DeleteMapping(path="/delete")
  public ResponseEntity<ResponseDto> deleteCard(@RequestParam String mobileNumber){
    boolean isDeleted = iCardsService.deleteCard(mobileNumber);
    if(isDeleted){
      return ResponseEntity
              .status(HttpStatus.OK)
              .body(new ResponseDto(CardConstants.STATUS_200, CardConstants.MESSAGE_200));
    }
    else{
      return ResponseEntity
              .status(HttpStatus.EXPECTATION_FAILED)
              .body(new ResponseDto(CardConstants.STATUS_417, CardConstants.MESSAGE_417_DELETE));
    }
    }
}
