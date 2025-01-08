package com.infy.dto;

import java.time.LocalDate;

import com.infy.entity.Accounts;

public class BankAccountDTO {

	 private Long accountNumber;
	    private String bankName;
	    private Double balance;
	    private String accountType;
	    private String ifscCode;
	    private LocalDate openingDate;
	    private UserDTO userDTO;
	    public BankAccountDTO(Accounts account) {
	        this.accountNumber = account.getAccountNumber();
	        this.bankName = account.getBankName();
	        this.balance = account.getBalance();
	        this.accountType = account.getAccountType();
	        this.ifscCode = account.getIfscCode();
	        this.openingDate = account.getOpeningDate();
	       if(account.getUser()!=null) {
	    	   UserDTO u =  new UserDTO(account.getUser());
	    	   this.userDTO=u;
	       }
	    }
		public Long getAccountNumber() {
			return accountNumber;
		}
		public void setAccountNumber(Long accountNumber) {
			this.accountNumber = accountNumber;
		}
		public String getBankName() {
			return bankName;
		}
		public void setBankName(String bankName) {
			this.bankName = bankName;
		}
		public Double getBalance() {
			return balance;
		}
		public void setBalance(Double balance) {
			this.balance = balance;
		}
		public String getAccountType() {
			return accountType;
		}
		public void setAccountType(String accountType) {
			this.accountType = accountType;
		}
		public String getIfscCode() {
			return ifscCode;
		}
		public void setIfscCode(String ifscCode) {
			this.ifscCode = ifscCode;
		}
		public LocalDate getOpeningDate() {
			return openingDate;
		}
		public void setOpeningDate(LocalDate openingDate) {
			this.openingDate = openingDate;
		}
		public UserDTO getUserDTO() {
			return userDTO;
		}
		public void setUserDTO(UserDTO userDTO) {
			this.userDTO = userDTO;
		}
	    public BankAccountDTO() {
			// TODO Auto-generated constructor stub
		}
}
