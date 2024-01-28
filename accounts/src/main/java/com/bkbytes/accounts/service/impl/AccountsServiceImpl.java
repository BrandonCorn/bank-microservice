package com.bkbytes.accounts.service.impl;

import com.bkbytes.accounts.constants.AccountsConstants;
import com.bkbytes.accounts.dto.AccountsDto;
import com.bkbytes.accounts.dto.CustomerDto;
import com.bkbytes.accounts.entity.Accounts;
import com.bkbytes.accounts.entity.Customer;
import com.bkbytes.accounts.exception.CustomerAlreadyExistsException;
import com.bkbytes.accounts.exception.ResourceNotFoundException;
import com.bkbytes.accounts.mapper.AccountsMapper;
import com.bkbytes.accounts.mapper.CustomerMapper;
import com.bkbytes.accounts.repository.AccountsRepository;
import com.bkbytes.accounts.repository.CustomerRepository;
import com.bkbytes.accounts.service.IAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements IAccountsService {

  private AccountsRepository accountsRepository;
  private CustomerRepository customerRepository;
  /**
   *
   * @param customerDto - CustomerDto Object
   */
  @Override
  public void createAccount(CustomerDto customerDto) {
    Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());
    Optional<Customer> optionalCustomer = customerRepository.findByMobileNumber(customerDto.getMobileNumber());
    if(optionalCustomer.isPresent()){
      throw new CustomerAlreadyExistsException(("Customer already registered with given mobileNumber")+customerDto.getMobileNumber());
    }

    Customer savedCustomer = customerRepository.save(customer);
    accountsRepository.save(createNewAccount(savedCustomer));


  }

  @Override
  public CustomerDto fetchAccount(String mobileNumber) {
    Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
            () -> new ResourceNotFoundException(
                    "Customer",
                    "mobileNumber",
                    mobileNumber
            )
    );

    Accounts account = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
            () -> new ResourceNotFoundException(
                    "Account",
                    "customerId",
                    customer.getCustomerId().toString()
            )
    );
    CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
    customerDto.setAccountsDto(AccountsMapper.mapToAccountsDto(account, new AccountsDto()));
    return customerDto;
  }

  /**
   *
   * @param customerDto - Input customerDto
   * @return boolean indicating if customer account update is successful or not
   */
  @Override
  public boolean updateAccount(CustomerDto customerDto) {
    boolean isUpdated = false;
    AccountsDto accountsDto = customerDto.getAccountsDto();
    if(accountsDto != null){
      Accounts accounts = accountsRepository.findById(accountsDto.getAccountNumber()).orElseThrow(
              () -> new ResourceNotFoundException(
                      "Account",
                      "AccountNumber",
                      accountsDto.getAccountNumber().toString()
              )
      );
      AccountsMapper.mapToAccounts(accountsDto, accounts);
      accounts = accountsRepository.save(accounts);

      Long customerId = accounts.getCustomerId();
      Customer customer = customerRepository.findById(customerId).orElseThrow(
              () -> new ResourceNotFoundException("Customer", "CustomerId", customerId.toString())
      );
      CustomerMapper.mapToCustomer(customerDto, customer);
      customerRepository.save(customer);
      isUpdated = true;
    }
    return isUpdated;
  }

  /**
   *
   * @param customerId - Input customerId
   * @return boolean indicating if customer account deleted successfully or not
   */
  @Override
  public boolean deleteAccount(String mobileNumber ) {
    Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
            () -> new ResourceNotFoundException("Customer", "MobileNumber", mobileNumber)
    );
    accountsRepository.deleteByCustomerId(customer.getCustomerId());
    customerRepository.deleteById(customer.getCustomerId());

    return true;
  }

  private Accounts createNewAccount(Customer customer){
    Accounts newAccount = new Accounts();
    newAccount.setCustomerId(customer.getCustomerId());
    long randomAcctNumber = 1000000000L + new Random().nextInt(900000000);

    newAccount.setAccountNumber(randomAcctNumber);
    newAccount.setAccountType(AccountsConstants.SAVINGS);
    newAccount.setBranchAddress(AccountsConstants.ADDRESS);

    return newAccount;
  }

}
