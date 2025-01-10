package com.imobile.entity;

import jakarta.persistence.*;

//import javax.persistence.*;

@Entity
public class DigitalBanking {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Integer digitalBankingId;
	    
	    private String accountType;
	    @ManyToOne(cascade = CascadeType.ALL)
	    @JoinColumn(name = "mobile_number")
	    private Users user;
	    
	    @OneToOne(cascade = CascadeType.ALL)
	    @JoinColumn(name = "account_number",unique = true)
	    private Accounts account;

		

		public Integer getDigitalBankingId() {
			return digitalBankingId;
		}

		public void setDigitalBankingId(Integer digitalBankingId) {
			this.digitalBankingId = digitalBankingId;
		}

		public String getAccountType() {
			return accountType;
		}

		public void setAccountType(String accountType) {
			this.accountType = accountType;
		}

		public Users getUser() {
			return user;
		}

		public void setUser(Users user) {
			this.user = user;
		}

		public Accounts getAccounts() {
			return account;
		}

		public void setAccounts(Accounts accountNumber) {
			this.account = accountNumber;
		}

		@Override
		public String toString() {
			return "DigitalBanking [digitalBankingId=" + digitalBankingId + ", accountType=" + accountType + ", user="
					+ user + ", account=" + account + "]";
		}
	    
	    
}
