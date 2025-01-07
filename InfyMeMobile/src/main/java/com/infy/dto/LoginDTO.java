package com.infy.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class LoginDTO {
	
	@NotNull(message = "{userdto.mobileNumber.null}")
	@Min(value = 1000000000L ,message = "{userdto.mobilenumber.invalid}")
	@Max(value = 9999999999L,message = "{userdto.mobilenumber.invalid}")
	private Long mobileNumber;
	
	 @NotNull(message = "{userdto.password.null}")
	 @Size(min = 5, max = 10, message = "{userdto.password.invalid.size}")
	 @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]+$", message = "{userdto.password.invalid}")
	 private String password;
	 
	public Long getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(Long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "LoginDTO [mobileNumber=" + mobileNumber + ", password=" + password + "]";
	}
	
}
