package com.infy.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infy.dto.AccountsDTO;
import com.infy.dto.BankAccountDTO;
import com.infy.dto.TransactionDTO;
import com.infy.entity.Accounts;
import com.infy.entity.DigitalBanking;
import com.infy.entity.Transactions;
import com.infy.entity.Users;
import com.infy.exception.InfyMeMobileException;
import com.infy.repository.AccountRepository;
import com.infy.repository.DigitalBankAccountRepository;
import com.infy.repository.TransactionRepository;
import com.infy.repository.UserRepository;
import com.infy.util.OTPGenerator;

@Service
public class AccountServiceImpl implements AccountService {
	
	@Autowired
	OTPGenerator generator;

	@Autowired
	UserRepository userRepository;
	
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private DigitalBankAccountRepository digitalBankAccountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public String createAccount(AccountsDTO accountDTO) throws InfyMeMobileException {
    	
    	Users user = userRepository.findByMobileNumber(accountDTO.getUserDTO().getMobileNumber()).orElseThrow(()->new InfyMeMobileException("Service.User.not.found"));
    	List<Accounts> accs =  accountRepository.findByBankNameAndAccountType(accountDTO.getBankName(),accountDTO.getAccountType());
    	if(accs!=null&& !accs.isEmpty() && accs.size()>0) {
    		throw new InfyMeMobileException("Service.Account_Already_Exist_In_Same_Bank_And_AccountType");
    	}
    	
        Accounts account = new Accounts(accountDTO);
        account.setUser(user);
        System.out.println(account.toString());
        Accounts savedAccount = accountRepository.save(account);
        return String.valueOf(savedAccount.getAccountNumber());
    }

    @Override
    public List<BankAccountDTO> listAccounts(Long mobileNo) throws InfyMeMobileException {
    	Optional<Users>  optional = userRepository.findById(mobileNo);
    	
    	optional.orElseThrow(()-> new InfyMeMobileException("Service.User.not.found"));
    	
    	List<Accounts> accounts = accountRepository.findByMobileNo(mobileNo);
       
    	if(accounts==null || accounts.isEmpty() || accounts.size()==0) throw new InfyMeMobileException("Service.User_Have_No_Account");
    	
        return accounts.stream()
                       .map(x-> new BankAccountDTO(x))
                       .collect(Collectors.toList());
    }

    @Override
    public String linkAccount(Long mobileNo, Long accountNo) throws InfyMeMobileException {
    	
    	Optional<Users>  optional = userRepository.findById(mobileNo);
    	Users u =optional.orElseThrow(()-> new InfyMeMobileException("Service.User.not.found"));
    	
    	
        
        Optional<Accounts> accountsOptional =accountRepository.findById(accountNo);
         Accounts ac=accountsOptional.orElseThrow(()-> new InfyMeMobileException("Service.Account.not.found"));
            	
        if(ac==null || ac.getUser()==null || !ac.getUser().getMobileNumber().equals(mobileNo) ) {
            		throw new InfyMeMobileException("Service.Account_Not_Belongs_To_This_User");
        }
       
        
       // check account already linked or not
        
        List<DigitalBanking> di =  digitalBankAccountRepository.findByMobileAndAccountNumber(mobileNo,accountNo);
        if(di!=null && !di.isEmpty()&&di.size()>0) throw new InfyMeMobileException("Service.Account_Mobile_Already_Linked");
        DigitalBanking digitalBankAccount = new DigitalBanking();
        
        digitalBankAccount.setAccountType(ac.getAccountType());
        digitalBankAccount.setAccounts(ac);
        digitalBankAccount.setUser(u);
        digitalBankAccountRepository.save(digitalBankAccount);
        return "Account_Link_Success";
    }

    @Override
    public String linkAccount(Long mobileNo, Long accountNo, Integer otp) throws InfyMeMobileException {

    	Optional<Users>  optional = userRepository.findById(mobileNo);
    	Users u =optional.orElseThrow(()-> new InfyMeMobileException("Service.User.not.found"));
    	
        
        Optional<Accounts> accountsOptional =accountRepository.findById(accountNo);
         Accounts ac=accountsOptional.orElseThrow(()-> new InfyMeMobileException("Service.Account.not.found"));
            	
        if(ac==null || ac.getUser()==null || !ac.getUser().getMobileNumber().equals(mobileNo) ) {
            		throw new InfyMeMobileException("Service.Account_Not_Belongs_To_This_User");
        }
       
//        if(!OTPGenerator.generateOTP()) throw new InfyMeMobileException("Service.Issue_in_OTP_Generation");
        
       // check account already linked or not
        
        List<DigitalBanking> di =  digitalBankAccountRepository.findByMobileAndAccountNumber(mobileNo,accountNo);
        if(di!=null && !di.isEmpty()&&di.size()>0) throw new InfyMeMobileException("Service.Account_Mobile_Already_Linked");
        DigitalBanking digitalBankAccount = new DigitalBanking();
        
        digitalBankAccount.setAccountType(ac.getAccountType());
        digitalBankAccount.setAccounts(ac);
        digitalBankAccount.setUser(u);
        
         generator.validateOTP(u.getEmail(),otp.toString());
        
//        if(!) throw new InfyMeMobileException("Service.OTP_DOESNOT_MATCH");
        digitalBankAccountRepository.save(digitalBankAccount);
        return "Account_Link_Success";
    }

    @Override
    public Double checkBalance(Long mobileNo, Long accountNo) throws InfyMeMobileException {
    	
    	Optional<Users>  optional = userRepository.findById(mobileNo);
    	Users u =optional.orElseThrow(()-> new InfyMeMobileException("Service.User.not.found"));
    	
    	
        Optional<Accounts> accounts = accountRepository.findById(mobileNo);
        
        Optional<Accounts> accountsOptional =accountRepository.findById(accountNo);
         Accounts ac=accountsOptional.orElseThrow(()-> new InfyMeMobileException("Service.Account.not.found"));
            	
        List<DigitalBanking> digitalBankAccounts = digitalBankAccountRepository.findByMobileAndAccountNumber(mobileNo, accountNo);
        if (digitalBankAccounts == null || digitalBankAccounts.isEmpty() || digitalBankAccounts.size()<=0) {
            throw new InfyMeMobileException("NO_ACCOUNT_IS_LINKED");
        }
        boolean flag =  true;
        for(DigitalBanking d : digitalBankAccounts) {
        	if( d!=null && d.getAccounts()!=null && d.getUser()!=null && d.getAccounts().getAccountNumber().equals(accountNo) && d.getAccounts().getUser().getUserId().equals(mobileNo)) {
        		flag= false;
        		break;
        	}
        }
        if(flag) throw new InfyMeMobileException("NO_ACCOUNT_IS_LINKED");
       return ac.getBalance();
    }

    @Override
    @Transactional
    public String fundTransfer(TransactionDTO transactionDTO) throws InfyMeMobileException {
    	 Optional<Users>  senderOptional = userRepository.findById(transactionDTO.getSender().getMobileNumber());
      	Users send =senderOptional.orElseThrow(()-> new InfyMeMobileException("Service.Sender.not.found"));
      	
      	 Optional<Users>  reciverOptional = userRepository.findById(transactionDTO.getSender().getMobileNumber());
       	Users recv =reciverOptional.orElseThrow(()-> new InfyMeMobileException("Service.Reciver.not.found"));
       	

    	Accounts senderAccount = accountRepository.findById(transactionDTO.getSenderAccount().getAccountNumber()).orElseThrow(() -> new InfyMeMobileException("Service.Sender_Account_Not_Present"));
    	 Accounts receiverAccount = accountRepository.findById(transactionDTO.getReceiverAccount().getAccountNumber()).orElseThrow(() -> new InfyMeMobileException("Service.Reciver_Account_Not_Present"));
    	 
    	
     	
        List<DigitalBanking> senderAccountLink = digitalBankAccountRepository.findByMobileAndAccountNumber(transactionDTO.getSender().getMobileNumber(), transactionDTO.getSenderAccount().getAccountNumber());
        
        
        List<DigitalBanking> receiverAccountLink = digitalBankAccountRepository.findByMobileAndAccountNumber(transactionDTO.getReceiver().getMobileNumber(), transactionDTO.getReceiverAccount().getAccountNumber());

        if (senderAccountLink == null || senderAccountLink.isEmpty() || senderAccountLink.size()<1 ) {
            throw new InfyMeMobileException("NO_ACCOUNT_IS_LINKED_Sender");
        }
        if (receiverAccountLink == null || receiverAccountLink.isEmpty() || receiverAccountLink.size()<1 ) {
            throw new InfyMeMobileException("NO_ACCOUNT_IS_LINKED_Reciver");
        }
        
//        Accounts senderAccount = accountRepository.findById(transactionDTO.getSenderAccount().getAccountNumber()).orElseThrow(() -> new InfyMeMobileException("Service.Sender_Not_Present"));
//        Accounts receiverAccount = accountRepository.findById(transactionDTO.getReceiverAccount().getAccountNumber()).orElseThrow(() -> new InfyMeMobileException("Service.Reciver_Not_Present"));

        if (senderAccount.getBalance() < transactionDTO.getAmount()) {
            throw new InfyMeMobileException("Service.INSUFFICIENT_FUNDS");
        }

        senderAccount.setBalance(senderAccount.getBalance() - transactionDTO.getAmount());
        receiverAccount.setBalance(receiverAccount.getBalance() + transactionDTO.getAmount());

        accountRepository.save(senderAccount);
        accountRepository.save(receiverAccount);

        Transactions transaction = new Transactions();
        
        transaction.setAmount(transactionDTO.getAmount());
        transaction.setModeOfTransaction(transactionDTO.getModeOfTransaction());
        transaction.setPaidTo(recv);
        transaction.setPaidFrom(send);
        transaction.setReceiverAccount(receiverAccount);
        transaction.setSenderAccount(senderAccount);
        transaction.setRemarks(transactionDTO.getRemarks());
        transaction.setTransactionDateTime(LocalDate.now());
//        transaction.set
        
        transactionRepository.save(transaction);

        return "Fund_transfer_successfull";
    }

    @Override
    public List<TransactionDTO> accountStatement(Long mobileNo) throws InfyMeMobileException {
        List<Transactions> transactions = transactionRepository.getAllTransaction(mobileNo);
        if (transactions.isEmpty()) {
            throw new InfyMeMobileException("NO_ACTIVE_TRANSACTIONS");
        }
        
        return transactions.stream()
                           .map(x-> new TransactionDTO(x))
                           .collect(Collectors.toList());
    }
}
