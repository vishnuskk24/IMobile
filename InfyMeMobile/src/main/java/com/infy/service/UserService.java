package com.infy.service;

import java.util.List;

import com.infy.dto.LoginDTO;
import com.infy.dto.UserDTO;
import com.infy.exception.InfyMeMobileException;
public interface UserService {

	 String createUser(UserDTO userDTO) throws InfyMeMobileException;
	    boolean loginUser(LoginDTO loginDTO) throws InfyMeMobileException;
	    UserDTO getUserProfile(String userId) throws InfyMeMobileException;
	    List<UserDTO> showAllUsers() throws InfyMeMobileException;
}
