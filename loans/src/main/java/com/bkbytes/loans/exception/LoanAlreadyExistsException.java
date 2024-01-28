package com.bkbytes.loans.exception;

public class LoanAlreadyExistsException extends RuntimeException{

  public LoanAlreadyExistsException(String errorMsg){
    super(errorMsg);
  }
}
