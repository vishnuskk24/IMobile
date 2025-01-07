package com.infy.entity;

import jakarta.persistence.*;

//import javax.persistence.*;

@Entity
public class DigitalBanking {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private String digitalBankingId;
	    
	    private String accountType;
	    @ManyToOne(cascade = CascadeType.ALL)
	    @JoinColumn(name = "mobile_number")
	    private Users user;
	    
	    @OneToOne(cascade = CascadeType.ALL)
	    @JoinColumn(name = "account_number",unique = true)
	    private Accounts account;

		

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
	    
	    
}
