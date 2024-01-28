package com.bkbytes.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(
        name = "Customer",
        description = "Schema to hold Customer and Account information"
)
public class CustomerDto {
  @Schema(
          description = "Name of the customer"
  )
  @NotEmpty(message = "Name should not be empty")
  @Size(max=30, min=5, message = "Length should be between 5 and 30")
  private String name;

  @Schema(
          description = "Email of the customer"
  )
  @NotEmpty(message = "Email should not be empty")
  @Email(message="Email address should be a valid value")
  private String email;

  @Schema(
          description = "Mobile number of the customer"
  )
  @Pattern(regexp="$|[0-9]{10}", message = "Mobile number must be 10 digits")
  private String mobileNumber;

  @Schema(
          description = "Account details of the customer"
  )
  private AccountsDto accountsDto;
}
