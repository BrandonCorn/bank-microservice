package com.bkbytes.loans.service.impl;

import com.bkbytes.loans.LoansApplication;
import com.bkbytes.loans.constants.LoanConstants;
import com.bkbytes.loans.controller.LoansController;
import com.bkbytes.loans.dto.LoansDto;
import com.bkbytes.loans.entity.Loans;
import com.bkbytes.loans.exception.LoanAlreadyExistsException;
import com.bkbytes.loans.exception.ResourceNotFoundException;
import com.bkbytes.loans.mapper.LoansMapper;
import com.bkbytes.loans.repository.LoansRepository;
import com.bkbytes.loans.service.ILoanService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class LoanServiceImpl implements ILoanService {

  private LoansRepository loansRepository;
  @Override
  public void createLoan(String mobileNumber) {
    Optional<Loans> optionalLoans = loansRepository.findByMobileNumber(mobileNumber);
    if(optionalLoans.isPresent()){
      throw new LoanAlreadyExistsException("Loan already exists");
    }
    else{
      Loans loans = createNewLoan(mobileNumber);
      loansRepository.save(loans);
    }
  }

  @Override
  public LoansDto fetchLoan(String mobileNumber) {
    Loans loans = loansRepository.findByMobileNumber(mobileNumber).orElseThrow(
            () -> new ResourceNotFoundException("Loan", "mobileNumber", mobileNumber)
    );
    return LoansMapper.mapToLoansDto(loans, new LoansDto());
  }

  @Override
  public boolean updateLoan(LoansDto loansDto) {
    Loans loans = loansRepository.findByLoanNumber(loansDto.getLoanNumber()).orElseThrow(
            () -> new ResourceNotFoundException("Loan", "loanNumber", loansDto.getLoanNumber())
    );
    LoansMapper.mapToLoans(loansDto, loans);
    loansRepository.save(loans);
    return true;
  }

  @Override
  public boolean deleteLoan(String mobileNumber) {
    Loans loans = loansRepository.findByMobileNumber(mobileNumber).orElseThrow(
            () -> new ResourceNotFoundException("Loan", "mobileNumber", mobileNumber)
    );
    loansRepository.delete(loans);
    return true;
  }

  private Loans createNewLoan(String mobileNumber){
    Loans loans = new Loans();
    String loanNumber = Long.toString(10000000 + new Random().nextInt(9000000));
    loans.setLoanNumber(loanNumber);
    loans.setMobileNumber(mobileNumber);
    loans.setLoanType(LoanConstants.PERSONAL_LOAN);
    loans.setTotalLoan(LoanConstants.STARTING_AMOUNT_PERSONAL_LOAN);
    loans.setOutstandingAmount(LoanConstants.STARTING_AMOUNT_PERSONAL_LOAN);
    loans.setAmountPaid(0);
    return loans;
  }
}
