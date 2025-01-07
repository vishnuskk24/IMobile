package com.infy.entity;



import java.time.LocalDate;

import com.infy.dto.AccountsDTO;

import jakarta.persistence.*;


//hosur.reddy
//import javax.persistence.*;
@Entity
public class Accounts {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long accountNumber;
	    private String bankName;
	    private Double balance;
	    private String accountType;
	    private String ifscCode;
	    private LocalDate openingDate;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "mobile_number")
    private Users user;

    Accounts(){
    	
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

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}
	public Accounts(AccountsDTO accountDTO) {
//        this.accountNumber = accountDTO.getAccountNumber();
        this.bankName = accountDTO.getBankName();
        this.balance = accountDTO.getBalance();
        this.accountType = accountDTO.getAccountType();
        this.ifscCode = accountDTO.getIfscCode();
        this.openingDate = accountDTO.getOpeningDate();
    }

}
