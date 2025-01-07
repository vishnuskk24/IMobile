package com.infy.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AccountsDTO {

	
		@NotNull(message = "{accountdto.bankname.null}")
		@NotBlank(message = "{accountdto.bankname.blank}")
	    private String bankName;
		@NotNull(message = "{accountdto.balance.null}")
		@Min(value = 1,message = "{accountdto.balance.invalid}")
	    private Double balance;
		
		@NotNull(message = "{accountdto.accountType.null}")
		@NotBlank(message = "{accountdto.accountType.blank}")
	    private String accountType;
		
		@NotNull(message = "{accountdto.ifscCode.null}")
		@NotBlank(message = "{accountdto.ifscCode.blank}")
	    private String ifscCode;
		
	    private LocalDate openingDate;
	    
	    private UserDTO userDTO;
		
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

	    // Constructors, getters, and setters
	
}
