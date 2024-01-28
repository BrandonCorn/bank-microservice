package com.bkbytes.loans.controller;

import com.bkbytes.loans.constants.LoanConstants;
import com.bkbytes.loans.dto.LoansDto;
import com.bkbytes.loans.dto.ResponseDto;
import com.bkbytes.loans.entity.Loans;
import com.bkbytes.loans.service.ILoanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.boot.actuate.autoconfigure.observation.ObservationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;

@Tag(
        name = "Loans REST API",
        description = "CRUD operations for Loans REST API"
)
@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Validated
public class LoansController {

  private ILoanService iLoanService;
  @Operation(
          summary = "Loan Fetch REST API",
          description = "Request to fetch Loan details"
  )
  @ApiResponses({
          @ApiResponse(
                  responseCode = "200",
                  description = "Http Status OK"
          ),
          @ApiResponse(
                  responseCode = "404",
                  description = "Http Status Bad Request"
          )
  })
  @PostMapping(path = "/create")
  public ResponseEntity<ResponseDto> createLoanRequest(@Valid @RequestParam @Pattern(regexp = "$|[0-9]{10}")
                                                         String mobileNumber){
    iLoanService.createLoan(mobileNumber);
    return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(new ResponseDto(LoanConstants.STATUS_201, LoanConstants.MESSAGE_201));
  }
  @Operation(
          summary = "Loan Fetch REST API",
          description = "Request to fetch Loan details"
  )
  @ApiResponses({
          @ApiResponse(
                  responseCode = "200",
                  description = "Http Status OK"
          ),
          @ApiResponse(
                  responseCode = "404",
                  description = "Http Status Bad Request"
          )
  })
  @GetMapping(path = "/fetch")
  public ResponseEntity<LoansDto> fetchLoanRequest(@RequestParam @Pattern(regexp = "$|[0-9]{10}")
                                   String mobileNumber){
    LoansDto foundLoan = iLoanService.fetchLoan(mobileNumber);
    return ResponseEntity
            .status(HttpStatus.FOUND)
            .body(foundLoan);
  }

  @Operation(
          summary = "Loan update REST API",
          description = "Request to update Loan details"
  )
  @ApiResponses({
          @ApiResponse(
                  responseCode = "200",
                  description = "Http Status OK"
          ),
          @ApiResponse(
                  responseCode = "417",
                  description = "Http Status Expectation Failed"
          )
  })
  @PutMapping(path = "/update")
  public ResponseEntity<ResponseDto> updateLoanRequest(@RequestBody LoansDto loansDto){
    boolean isUpdated = iLoanService.updateLoan(loansDto);
    if(isUpdated){
      return ResponseEntity
              .status(HttpStatus.OK)
              .body(new ResponseDto(LoanConstants.STATUS_200, LoanConstants.MESSAGE_200));
    }
    else{
      return ResponseEntity
              .status(HttpStatus.EXPECTATION_FAILED)
              .body(new ResponseDto(LoanConstants.STATUS_417, LoanConstants.MESSAGE_417_UPDATE));
    }
  }

  @Operation(
          summary = "Loan delete REST API",
          description = "Request to delete Loan details"
  )
  @ApiResponses({
          @ApiResponse(
                  responseCode = "200",
                  description = "Http Status OK"
          ),
          @ApiResponse(
                  responseCode = "404",
                  description = "Http Status Expectation Failed"
          )
  })
  @DeleteMapping(path = "/delete")
  public ResponseEntity<ResponseDto> deleteLoanRequest(@RequestParam String mobileNumber){
    boolean isDeleted = iLoanService.deleteLoan(mobileNumber);
    if(isDeleted){
      return ResponseEntity
              .status(HttpStatus.OK)
              .body(new ResponseDto(LoanConstants.STATUS_200, LoanConstants.MESSAGE_200));
    }
    else{
      return ResponseEntity
              .status(HttpStatus.EXPECTATION_FAILED)
              .body(new ResponseDto(LoanConstants.STATUS_417, LoanConstants.MESSAGE_417_DELETE));
    }
  }
}
