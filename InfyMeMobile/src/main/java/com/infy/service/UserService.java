package com.infy.service;

import java.util.List;

import com.infy.dto.LoginDTO;
import com.infy.dto.UserDTO;
import com.infy.exception.IMobileException;
public interface UserService {

	 String createUser(UserDTO userDTO) throws IMobileException;
	    String loginUser(LoginDTO loginDTO) throws IMobileException;
	    UserDTO getUserProfile(String userId) throws IMobileException;
	    List<UserDTO> showAllUsers() throws IMobileException;
	    void checkUser(String mail) throws IMobileException;
		String sendOTPByMail(String mail) throws IMobileException;
		String validateOTPByMail(String mail, String OTP, String pasword) throws IMobileException;
}
