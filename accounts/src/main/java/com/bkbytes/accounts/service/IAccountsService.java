package com.bkbytes.accounts.service;

import com.bkbytes.accounts.dto.CustomerDto;

public interface IAccountsService {

  /**
   *
   * @param customerDto - CustomerDto Object
   */
  void createAccount(CustomerDto customerDto);

  /**
   *
   * @param mobileNumber - Input Mobile Number
   * @return - Accounts Details based on a given mobileNumber
   */
  CustomerDto fetchAccount(String mobileNumber);

  /**
   *
   * @param customerDto - Input customerDto
   * @return boolean indicating if the update of Account details is successful or not
   */
  boolean updateAccount(CustomerDto customerDto);

  boolean deleteAccount(String mobileNumber);
}
