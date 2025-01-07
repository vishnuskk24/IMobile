package com.infy.service;

import java.util.List;

import com.infy.dto.AccountsDTO;
import com.infy.dto.BankAccountDTO;
import com.infy.dto.TransactionDTO;
import com.infy.exception.InfyMeMobileException;

public interface AccountService {

	String createAccount(AccountsDTO accountDTO)throws InfyMeMobileException;

	List<BankAccountDTO> listAccounts(Long mobileNo) throws InfyMeMobileException;

	String linkAccount(Long mobileNo, Long accountNo)throws InfyMeMobileException;

	String linkAccount(Long mobileNo, Long accountNo, Integer otp)throws InfyMeMobileException;

	Double checkBalance(Long mobileNo, Long accountNo) throws InfyMeMobileException;

	List<TransactionDTO> accountStatement(Long mobileNo)  throws InfyMeMobileException;

	String fundTransfer(TransactionDTO transactionDTO)throws InfyMeMobileException;

}
