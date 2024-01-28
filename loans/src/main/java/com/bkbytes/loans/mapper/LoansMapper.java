package com.bkbytes.loans.mapper;

import com.bkbytes.loans.dto.LoansDto;
import com.bkbytes.loans.entity.Loans;

public class LoansMapper {

  public static Loans mapToLoans(LoansDto loansDto, Loans loans){
    loans.setLoanNumber(loansDto.getLoanNumber());
    loans.setLoanType(loansDto.getLoanType());
    loans.setTotalLoan(loansDto.getTotalLoan());
    loans.setAmountPaid(loansDto.getAmountPaid());
    loans.setOutstandingAmount(loansDto.getOutstandingAmount());
    loans.setMobileNumber(loansDto.getMobileNumber());
    return loans;
  }

  public static LoansDto mapToLoansDto(Loans loans, LoansDto loansDto){
    loansDto.setLoanNumber(loans.getLoanNumber());
    loansDto.setLoanType(loans.getLoanType());
    loansDto.setTotalLoan(loans.getTotalLoan());
    loansDto.setAmountPaid(loans.getAmountPaid());
    loansDto.setOutstandingAmount(loans.getOutstandingAmount());
    loansDto.setMobileNumber(loans.getMobileNumber());
    return loansDto;
  }
}
