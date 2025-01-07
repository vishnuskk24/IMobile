package com.infy.api;

import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.infy.exception.InfyMeMobileException;
import com.infy.util.OTPGenerator;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@RestController
@CrossOrigin(origins = "*") 
public class OtpAPI {

	
	private final OTPGenerator otpGenerator;
    private final JavaMailSender javaMailSender;

    
    public OtpAPI(OTPGenerator otpGenerator, JavaMailSender javaMailSender) {
        this.otpGenerator = otpGenerator;
        this.javaMailSender = javaMailSender;
    }
    
    // OTP sender to the specfic mail
    @Operation
    @ApiResponses
    @PostMapping("/send-otp")
    public ResponseEntity<String> sendOTP( @NotNull(message = "{userdto.email.null}")
								    	    @Pattern(regexp = "^[\\w.]+@[\\w]+\\.[\\w]+$", message = "{userdto.email.invalid}")
								    	    @RequestParam String email) {
    	
        String otp = otpGenerator.generateOTP(email);
        
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("OTP for your application");
        message.setText("Your OTP is: " + otp);
        
        javaMailSender.send(message);
        
        return ResponseEntity.ok("OTP sent successfully.");
    }
    
    @GetMapping(value = "/validate-otp/{email}/{otp}")
    public ResponseEntity<String> validateOTP(@PathVariable String email,@PathVariable String otp) throws InfyMeMobileException{
    	
    	otpGenerator.validateOTP(otp , email);
    	
    	 return ResponseEntity.ok("OTP validated successfully.");
    }
}
