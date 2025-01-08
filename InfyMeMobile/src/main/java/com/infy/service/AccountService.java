package com.infy.service;

import java.util.List;

import com.infy.dto.AccountsDTO;
import com.infy.dto.BankAccountDTO;
import com.infy.dto.TransactionDTO;
import com.infy.exception.IMobileException;

public interface AccountService {

	String createAccount(AccountsDTO accountDTO)throws IMobileException;

	List<BankAccountDTO> listAccounts(Long mobileNo) throws IMobileException;

	String linkAccount(Long mobileNo, Long accountNo)throws IMobileException;

	String linkAccount(Long mobileNo, Long accountNo, Integer otp)throws IMobileException;

	Double checkBalance(Long mobileNo, Long accountNo) throws IMobileException;

	List<TransactionDTO> accountStatement(Long mobileNo)  throws IMobileException;

	String fundTransfer(TransactionDTO transactionDTO)throws IMobileException;

}
