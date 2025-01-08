package com.infy.util;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class CustomMailService {
	 private final JavaMailSender javaMailSender;

	CustomMailService( JavaMailSender javaMailSender){
		 this.javaMailSender = javaMailSender;
	}
	
	public String sendOTPInMail(String email,String otp) {
	   
	       try {
	    	   
	           SimpleMailMessage message = new SimpleMailMessage();
	           message.setTo(email);
	           message.setSubject("OTP for your application");
	           message.setText("Your OTP is: " + otp);
	           
	           javaMailSender.send(message);
	           
		} catch (Exception e) {
			System.out.println(e);
		}
	       return "Service.Mail_OTP_Send_Success";
	}
}
