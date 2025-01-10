package com.imobile.service;

import java.util.List;

import com.imobile.dto.AccountsDTO;
import com.imobile.dto.BankAccountDTO;
import com.imobile.dto.TransactionDTO;
import com.imobile.exception.IMobileException;

public interface AccountService {

	String createAccount(AccountsDTO accountDTO)throws IMobileException;

	List<BankAccountDTO> listAccounts(Long mobileNo) throws IMobileException;

	String linkAccount(Long mobileNo, Long accountNo)throws IMobileException;

	String linkAccount(Long mobileNo, Long accountNo, Integer otp)throws IMobileException;

	Double checkBalance(Long mobileNo, Long accountNo) throws IMobileException;

	List<TransactionDTO> accountStatement(Long mobileNo)  throws IMobileException;

	String fundTransfer(TransactionDTO transactionDTO)throws IMobileException;

}
