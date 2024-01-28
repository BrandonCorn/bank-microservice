package com.bkbytes.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(
        name = "Account",
        description = "Schema to hold Account information"
)
public class AccountsDto {

  @Schema(
          description = "Customer Account's account number"
  )
  @Pattern(regexp="$|[0-9]{10}", message = "Account number should be 10 digits")
  @NotEmpty
  private Long accountNumber;

  @Schema(
          description = "Customer Account's account type"
  )
  @NotEmpty(message = "Account type cannot be empty")
  private String accountType;

  @Schema(
          description = "Customer Account's branch address"
  )
  @NotEmpty(message = "Branch address cannot be empty")
  private String branchAddress;
}
