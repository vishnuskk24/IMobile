package com.imobile.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.imobile.exception.IMobileException;
import com.imobile.service.UserService;
import com.imobile.util.OTPGeneratorService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@RestController
@CrossOrigin(origins = "*") 
public class OtpAPI {

	@Autowired
	Environment environment;
	@Autowired
	UserService userService;
	private final OTPGeneratorService otpGeneratorService;
	
//	@Autowired
//    private final JavaMailSender javaMailSender;

    
    public OtpAPI(OTPGeneratorService otpGenerator) {
        this.otpGeneratorService = otpGenerator;
//        this.javaMailSender = javaMailSender;
    }
    
    // OTP sender to the specfic mail
    @Operation
    @ApiResponses
    @PostMapping("/send-otp")
    public ResponseEntity<String> sendOTP( @NotNull(message = "{userdto.email.null}")
								    	    @Pattern(regexp = "^[\\w.]+@[\\w]+\\.[\\w]+$", message = "{userdto.email.invalid}")
								    	    @RequestParam String email) throws IMobileException {
    	
    	
      String s  =userService.sendOTPByMail(email);
   
        return ResponseEntity.ok(environment.getProperty(s));
    }
    
    @GetMapping(value = "/validate-otp/{email}/{otp}/{password}")
    public ResponseEntity<String> validateOTP(@PathVariable@Email(message = "{api.forget.password.email.invalid}") String email,@PathVariable String otp,@PathVariable @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",message = "{api.forgetpassword.invalid}") String password) throws IMobileException{
    	String s =userService.validateOTPByMail(email, otp, password);
    	
    	
    	return ResponseEntity.ok(environment.getProperty(s));
    }
}
