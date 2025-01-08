package com.infy.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.infy.dto.AccountsDTO;
import com.infy.dto.BankAccountDTO;
import com.infy.dto.TransactionDTO;
import com.infy.exception.IMobileException;
import com.infy.service.AccountService;

import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@RestController
@RequestMapping("/accounts")
@Validated
@CrossOrigin(origins = "*") 
public class AccountAPI {

    @Autowired
    private AccountService accountService;
    @Operation
    @ApiResponses
    @PostMapping
    public ResponseEntity<String> createAccount(@Valid @RequestBody AccountsDTO accountDTO) throws IMobileException {
    	System.out.println(accountDTO.toString());
       String accNo= accountService.createAccount(accountDTO);
        return ResponseEntity.ok("Account created successfully with AccountNo "+accNo);
    }
    @Operation
    @ApiResponses
    @GetMapping("/{mobileNo}")
    public ResponseEntity<List<BankAccountDTO>> listAccounts(@NotNull(message = "{userdto.mobileNumber.null}")
															@Min(value = 1000000000L ,message = "{userdto.mobilenumber.invalid}")
															@Max(value = 9999999999L,message = "{userdto.mobilenumber.invalid}")
    														@PathVariable Long mobileNo) throws IMobileException {
        List<BankAccountDTO> accounts = accountService.listAccounts(mobileNo);
        return ResponseEntity.ok(accounts);
    }
    @Operation
    @ApiResponses
    @PostMapping("/{mobileNo}/link")
    public ResponseEntity<String> linkAccount(@NotNull(message = "{userdto.mobileNumber.null}")
 											  @Min(value = 1000000000L ,message = "{userdto.mobilenumber.invalid}")
											 @Max(value = 9999999999L,message = "{userdto.mobilenumber.invalid}")@PathVariable Long mobileNo, 
											 @NotNull(message = "{userdto.mobileNumber.null}")
											  @Min(value = 1 ,message = "{accountdto.accountNo.invalid}")
											 @RequestParam Long accountNo) throws IMobileException {
        accountService.linkAccount(mobileNo, accountNo);
        return ResponseEntity.ok("Account linked successfully");
    }
    @Operation
    @ApiResponses
    @PostMapping("/{mobileNo}/link/otp")
    public ResponseEntity<String> linkAccount(@NotNull(message = "{userdto.mobileNumber.null}")
											  @Min(value = 1000000000L ,message = "{userdto.mobilenumber.invalid}")
											  @Max(value = 9999999999L,message = "{userdto.mobilenumber.invalid}")@PathVariable Long mobileNo, 
											 
											  @NotNull(message = "{userdto.mobileNumber.null}")
											  @Min(value = 1 ,message = "{accountdto.accountNo.invalid}")
    										  @RequestParam Long accountNo, 
    										  @NotNull(message = "{userdto.otp.null}")
    										  @RequestParam Integer otp) throws IMobileException {
        accountService.linkAccount(mobileNo, accountNo, otp);
        return ResponseEntity.ok("Account linked with OTP successfully");
    }
    @Operation
    @ApiResponses
    @GetMapping("/balance/{mobileNo}")
    public ResponseEntity<Double> checkBalance(@NotNull(message = "{userdto.mobileNumber.null}")
											  @Min(value = 1000000000L ,message = "{userdto.mobilenumber.invalid}")
											  @Max(value = 9999999999L,message = "{userdto.mobilenumber.invalid}")@PathVariable Long mobileNo,
											
											  @NotNull(message = "{userdto.mobileNumber.null}")
											  @Min(value = 1 ,message = "{accountdto.accountNo.invalid}")
											  @RequestParam Long accountNo) throws IMobileException {
        Double balance = accountService.checkBalance(mobileNo, accountNo);
        return ResponseEntity.ok(balance);
    }
    @Operation
    @ApiResponses()
    @PutMapping("/fundtransfer")
    public ResponseEntity<String> fundTransfer(@Valid @RequestBody TransactionDTO transactionDTO) throws IMobileException {
        accountService.fundTransfer(transactionDTO);
        return ResponseEntity.ok("Fund transfer successful");
    }
    @Operation
    @ApiResponses
    @GetMapping("/statement/{mobileNo}")
    public ResponseEntity<List<TransactionDTO>> accountStatement(@NotNull(message = "{userdto.mobileNumber.null}")
																  @Min(value = 1000000000L ,message = "{userdto.mobilenumber.invalid}")
																  @Max(value = 9999999999L,message = "{userdto.mobilenumber.invalid}")@PathVariable Long mobileNo) throws IMobileException {
        List<TransactionDTO> statements = accountService.accountStatement(mobileNo);
        return ResponseEntity.ok(statements);
    }
    
    public  ResponseEntity<String> forgetPassword(){
    	
    	
    	return new ResponseEntity<>("Forget_Password_Request",HttpStatus.OK);
    }
}
