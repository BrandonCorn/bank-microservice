package com.bkbytes.loans.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.stereotype.Component;

@Schema(
        name = "Loans",
        description = "Schema for Loans"
)
@Data
public class LoansDto {

  @Schema(description = "Mobile number associated with Loan")
  @NotEmpty(message = "Must provide a mobile number")
  @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile Number must be 10 digits")
  private String mobileNumber;

  @Schema(description = "Loan number")
  @NotEmpty(message = "Must provide a loan number")
  private String loanNumber;

  @Schema(description = "Type of loan")
  @NotEmpty(message = "Must provide a loan type")
  private String loanType;

  @Schema(description = "Amount paid on loan")
  @NotEmpty(message = "Must provide an amount paid")
  private int amountPaid;

  @Schema(description = "Amount still owed on loan")
  @NotEmpty(message = "Must provide an outstanding amount")
  private int outstandingAmount;

  @Schema(description = "Total amount of the loan")
  @NotEmpty(message = "Must provide total loan amount")
  private int totalLoan;
}
