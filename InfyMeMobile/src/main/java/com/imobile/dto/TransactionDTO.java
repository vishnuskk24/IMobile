package com.imobile.dto;

import java.time.LocalDate;

import com.imobile.entity.Transactions;
import com.imobile.entity.Users;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

public class TransactionDTO {

	
	private Integer transactionId;
	
	@NotNull(message = "{transactiondto.modeOfTransaction.null}")
	@NotBlank(message = "{transactiondto.modeOfTransaction.null}")
    private String modeOfTransaction;
	
	
	@NotNull(message = "{transactiondto.amount.null}")
	@Min(value = 1 , message = "{transactiondto.amount.invalid}")
    private Double amount;
	
	@NotNull(message = "{transactiondto.transactionDateTime.null}")
	@PastOrPresent(message = "{transactiondto.transactionDateTime.invalid}")
    private LocalDate transactionDateTime;
	
    private String remarks;
    @NotNull(message = "{transactiondto.receiver.null}")
    private UserDTO receiver;
    
    @NotNull(message = "{transactiondto.receiverAccount.null}")
    private BankAccountDTO receiverAccount;

    @NotNull(message = "{transactiondto.senderAccount.null}")
    private BankAccountDTO senderAccount;
    
    @NotNull(message = "{transactiondto.sender.null}")
    private UserDTO  sender;
    
   
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
    
	public TransactionDTO() {
		// TODO Auto-generated constructor stub
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
