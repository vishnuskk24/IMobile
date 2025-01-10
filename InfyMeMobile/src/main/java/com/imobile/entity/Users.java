package com.imobile.entity;

import java.time.LocalDate;

import com.imobile.dto.UserDTO;

import jakarta.persistence.*;

//import javax.persistence.*;

@Entity
public class Users {
		@Id
//		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long mobileNumber;
		private String userId;
		private String accountHolderName;
		@Enumerated(EnumType.STRING)
		private Gender gender;
		private LocalDate dateOfBirth;
		private String password;
		private String email;
		private String communicationAddress;
		private String PAN;
		
		public Users(UserDTO userDTO) {

			 this.mobileNumber = userDTO.getMobileNumber();
			 this.userId = userDTO.getUserId();
		        this.accountHolderName = userDTO.getAccountHolderName();
		        this.gender = userDTO.getGender();
		        this.dateOfBirth = userDTO.getDateOfBirth();
		        this.password = userDTO.getPassword();
		        this.email = userDTO.getEmail();
		        this.communicationAddress = userDTO.getCommunicationAddress();
		        this.PAN = userDTO.getPAN();
				
		}
		
		public Users() {
			// TODO Auto-generated constructor stub
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
			return "Users [mobileNumber=" + mobileNumber + ", userId=" + userId + ", accountHolderName="
					+ accountHolderName + ", gender=" + gender + ", dateOfBirth=" + dateOfBirth + ", password="
					+ password + ", email=" + email + ", communicationAddress=" + communicationAddress + ", PAN=" + PAN
					+ "]";
		}
		
		
	
}
