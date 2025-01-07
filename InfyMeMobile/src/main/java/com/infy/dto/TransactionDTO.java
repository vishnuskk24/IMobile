package com.infy.dto;

import java.time.LocalDate;

import com.infy.entity.Transactions;
import com.infy.entity.Users;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

public class TransactionDTO {

	
	private Integer transactionId;
	@NotNull(message = "{tansactiondto.modeOfTransaction.null}")
	@NotBlank(message = "{tansactiondto.modeOfTransaction.null}")
    private String modeOfTransaction;
	@NotNull(message = "{tansactiondto.amount.null}")
	@Min(value = 1 , message = "{tansactiondto.amount.invalid}")
    private Double amount;
	@NotNull(message = "{tansactiondto.transactionDateTime.null}")
	@PastOrPresent(message = "{tansactiondto.transactionDateTime.invalid}")
    private LocalDate transactionDateTime;
	
    private String remarks;
    @NotNull(message = "{tansactiondto.receiver.null}")
    private UserDTO receiver;
    @NotNull(message = "{tansactiondto.transactionDateTime.null}")
    private BankAccountDTO senderAccount;
    @NotNull(message = "{tansactiondto.sender.null}")
    private UserDTO  sender;
    @NotNull(message = "{tansactiondto.receiverAccount.null}")
    private BankAccountDTO receiverAccount;

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

	public UserDTO getReceiver() {
		return receiver;
	}

	public void setReceiver(UserDTO receiver) {
		this.receiver = receiver;
	}

	public BankAccountDTO getSenderAccount() {
		return senderAccount;
	}

	public void setSenderAccount(BankAccountDTO senderAccount) {
		this.senderAccount = senderAccount;
	}

	public UserDTO getSender() {
		return sender;
	}

	public void setSender(UserDTO sender) {
		this.sender = sender;
	}

	public BankAccountDTO getReceiverAccount() {
		return receiverAccount;
	}

	public void setReceiverAccount(BankAccountDTO receiverAccount) {
		this.receiverAccount = receiverAccount;
	}
    
	 public TransactionDTO(Transactions transaction) {
	        this.transactionId = transaction.getTransactionId();
	        this.modeOfTransaction = transaction.getModeOfTransaction();
	        this.amount = transaction.getAmount();
	        this.transactionDateTime = transaction.getTransactionDateTime();
	        this.remarks = transaction.getRemarks();
	        if(transaction.getPaidFrom()!=null) this.sender = new UserDTO(transaction.getPaidFrom());
	        if (transaction.getPaidTo()!=null) this.receiver =  new UserDTO(transaction.getPaidTo());;
	        if(transaction.getSenderAccount()!=null)this.senderAccount = new BankAccountDTO(transaction.getSenderAccount());
	        if(transaction.getReceiverAccount()!=null) this.receiverAccount = new BankAccountDTO(transaction.getReceiverAccount());
	    }
    
}
