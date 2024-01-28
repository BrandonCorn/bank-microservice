package com.bkbytes.cards.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(
        name = "Cards",
        description = "Customer card details"
)
public class CardsDto {

  @Schema(description = "Mobile number of customer")
  @NotEmpty(message = "Must provide a mobile number")
  @Pattern(regexp = "$|[0-9]{10}", message = "Mobile number must be 10 digits long")
  private String mobileNumber;

  @Schema(description = "Card number of customer")
  @NotEmpty
  @Pattern(regexp = "$|[0-9]{16}", message = "Must be a valid 16 digit number")
  private String cardNumber;

  @Schema(description = "Type of card")
  @NotEmpty
  private String cardType;

  @Schema(description = "Total limit of the card")
  @NotEmpty
  private int totalLimit;

  @Schema(description = "Amount of card limit used")
  @NotEmpty
  private int amountUsed;

  @Schema(description = "Amount of card funds available")
  @NotEmpty
  private int availableAmount;
}
