package com.infy.entity;

import java.time.LocalDate;

import jakarta.persistence.*;


//import javax.persistence.*;

@Entity
public class Transactions {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer transactionId;
    private String modeOfTransaction;
 
   
    private Double amount;
    private LocalDate transactionDateTime;
    private String remarks;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "receiver_account_number")
    private Accounts receiverAccount;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "paid_to")
    private Users paidTo;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sender_account_number")
    private Accounts senderAccount;
    
    public Integer getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Integer transactionId) {
		this.transactionId = transactionId;
	}

	public String getModeOfTransaction() {
		return modeOfTransaction;
	}

	public void setModeOfTransaction(String modeOfTransaction) {
		this.modeOfTransaction = modeOfTransaction;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public LocalDate getTransactionDateTime() {
		return transactionDateTime;
	}

	public void setTransactionDateTime(LocalDate transactionDateTime) {
		this.transactionDateTime = transactionDateTime;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Accounts getReceiverAccount() {
		return receiverAccount;
	}

	public void setReceiverAccount(Accounts receiverAccount) {
		this.receiverAccount = receiverAccount;
	}

	public Users getPaidTo() {
		return paidTo;
	}

	public void setPaidTo(Users paidTo) {
		this.paidTo = paidTo;
	}

	public Accounts getSenderAccount() {
		return senderAccount;
	}

	public void setSenderAccount(Accounts senderAccount) {
		this.senderAccount = senderAccount;
	}

	public Users getPaidFrom() {
		return paidFrom;
	}

	public void setPaidFrom(Users paidFrom) {
		this.paidFrom = paidFrom;
	}

	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "paid_from")
    private Users paidFrom;
    
   

}
