package com.bkbytes.loans.service;

import com.bkbytes.loans.dto.LoansDto;
import org.springframework.stereotype.Service;

@Service
public interface ILoanService {

  void createLoan(String mobileNumber);

  LoansDto fetchLoan(String mobileNumber);

  boolean updateLoan(LoansDto loansDto);

  boolean deleteLoan(String mobileNumber);

}
