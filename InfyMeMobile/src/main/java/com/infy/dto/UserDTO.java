package com.infy.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.infy.entity.Gender;
import com.infy.entity.Users;

import jakarta.validation.constraints.*;


//import javax.validation.constraints.*;

public class UserDTO {
	
//	@JsonProperty( "mobileNumber")
	@NotNull(message = "{userdto.mobileNumber.null}")
	@Min(value = 1000000000L ,message = "{userdto.mobilenumber.invalid}")
	@Max(value = 9999999999L,message = "{userdto.mobilenumber.invalid}")
    private Long mobileNumber;

//	@JsonProperty( "userId")
	@NotNull(message = "{userdto.userId.null}")
	@NotBlank(message = "{userdto.userId.blank}")
//    @Pattern(regexp = "^U.*", message = "{userdto.userId.invalid}")
    private String userId;

//    @JsonProperty( "accountHolderName")
	@NotNull(message = "{userdto.accountHolderName.null}")
	@NotBlank(message = "{userdto.accountHolderName.blank}")
    @Size(min = 3, max = 50, message = "{userdto.accountHolderName.invalid}")
    private String accountHolderName;
    
//    @JsonProperty( "gender")
	@NotNull(message = "{userdto.gender.null}")
//    @Pattern(regexp = "^(Male|Female)$", message = "{userdto.gender.invalid}")
    private Gender gender;

//    @JsonProperty( "dateOfBirth")
    @NotNull(message = "{userdto.dateOfBirth.null}")
    @Past(message = "{userdto.dateOfBirth.invalid}")
    private LocalDate dateOfBirth;
 
//    @JsonProperty( "password")
    @NotNull(message = "{userdto.password.null}")
    @Size(min = 5, max = 30, message = "{userdto.password.invalid.size}")
    private String password;
    
//    @JsonProperty( "email")
    @NotNull(message = "{userdto.email.null}")
    @Email(message = "{userdto.email.invalid}")
    private String email;
    
//    @JsonProperty( "communicationAddress")
    @NotBlank(message = "{userdto.communicationAddress.null}")
    @Size(min = 3, max = 50, message = "{userdto.communicationAddress.invalid}")
    private String communicationAddress;
   
    @JsonProperty( "PAN")
    @NotBlank(message ="{userdto.PAN.null}")
//    @Pattern(regexp = "^[A-Z]{5}\\d{4}[A-Z]$", message = "{userdto.PAN.invalid}")
    private String PAN;
	
    UserDTO(){
    	System.out.println("default constructor");
    }
	public UserDTO(Users users) {
		if(users!=null) {
			this.mobileNumber = users.getMobileNumber();
			this.userId =users.getUserId();
		    this.accountHolderName = users.getAccountHolderName();
		    this.gender = users.getGender();
		    this.dateOfBirth = users.getDateOfBirth();
		    this.password = users.getPassword();
		    this.email = users.getEmail();
		    this.communicationAddress = users.getCommunicationAddress();
		    this.PAN = users.getPAN();
		}
	    }

	public Long getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(Long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAccountHolderName() {
		return accountHolderName;
	}

	public void setAccountHolderName(String accountHolderName) {
		this.accountHolderName = accountHolderName;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCommunicationAddress() {
		return communicationAddress;
	}

	public void setCommunicationAddress(String communicationAddress) {
		this.communicationAddress = communicationAddress;
	}

	public String getPAN() {
		return PAN;
	}

	public void setPAN(String pAN) {
		PAN = pAN;
	}
	

	@Override
	public String toString() {
		return "UserDTO [mobileNumber=" + mobileNumber + ", userId=" + userId + ", accountHolderName="
				+ accountHolderName + ", gender=" + gender + ", dateOfBirth=" + dateOfBirth + ", password=" + password
				+ ", email=" + email + ", communicationAddress=" + communicationAddress + ", PAN=" + PAN + "]";
	}

		
	
	
}
