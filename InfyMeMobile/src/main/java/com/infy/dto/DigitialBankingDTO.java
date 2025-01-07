package com.infy.dto;

import com.infy.entity.Accounts;
import com.infy.entity.Users;

public class DigitialBankingDTO {

	private String digitalBankingId;
    private String accountType;
	 private UserDTO userDTO;
	 private AccountsDTO accountDTO;
	public String getDigitalBankingId() {
		return digitalBankingId;
	}
	public void setDigitalBankingId(String digitalBankingId) {
		this.digitalBankingId = digitalBankingId;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public UserDTO getUserDTO() {
		return userDTO;
	}
	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}
	public AccountsDTO getAccountDTO() {
		return accountDTO;
	}
	public void setAccountDTO(AccountsDTO accountDTO) {
		this.accountDTO = accountDTO;
	}
	 
}
