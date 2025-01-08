 package com.infy.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infy.dto.LoginDTO;
import com.infy.dto.UserDTO;
import com.infy.entity.Users;
import com.infy.exception.InfyMeMobileException;
import com.infy.repository.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	
		@Autowired
	  private  UserRepository userRepository;

	    public UserServiceImpl(UserRepository userRepository) {
	        this.userRepository = userRepository;
	    }

	    @Override
	    public String createUser(UserDTO userDTO) throws InfyMeMobileException {
	        
	            // Check if the Users already exists
	    	System.out.println("service lie 33 -> " + userDTO.toString());
	            Optional<Users> existingUser = userRepository.findByMobileNumber(userDTO.getMobileNumber());
	            if (existingUser.isPresent()) {
	                throw new InfyMeMobileException("Users already exists");
	            }
	            
	            // Save the new Users
	            Users newUser = new Users(userDTO);
	            
	            userRepository.save(newUser);
	            return newUser.getMobileNumber()+""; // Return the mobile number of the created Users
	        
	    }

	    @Override
	    public boolean loginUser(LoginDTO loginDTO) throws InfyMeMobileException {
	        // Find the Users by mobile number
	    	System.out.println(loginDTO + "  service line 50" + Thread.currentThread().getStackTrace()[2].getLineNumber());
	        Optional<Users> userOptional = userRepository.findByMobileNumber(loginDTO.getMobileNumber());
	        if (userOptional.isPresent()) {
	            Users Users = userOptional.get();
	            // Compare passwords
	            if (Users.getPassword().equals(loginDTO.getPassword())) {
	            	System.out.println("User logi success ful login service");
	                return true; // Authentication successful
	            }
	        }
	        // Authentication failed
	        throw new InfyMeMobileException("Authentication_failed");
	    }

	    @Override
	    public UserDTO getUserProfile(String userId) throws InfyMeMobileException {
	    	System.out.println("line 65 in service");
	        Optional<Users> userOptional = userRepository.findByUserId(userId);
	        if (userOptional.isPresent()) {
	            Users Users = userOptional.get();
//	            return new UserDTO(Users); // Convert Users entity to UserDTO and return
	            return null;
	        } else {
	            throw new InfyMeMobileException("Users_ID_not_found");
	        }
	    }

	    @Override
	    public List<UserDTO> showAllUsers() throws InfyMeMobileException {
	        List<Users> users = userRepository.findAll();
	        if (users.isEmpty()) {
	            throw new InfyMeMobileException("No_users_found");
	        }
	        return users.stream()
	                .map(x-> new UserDTO(x)) // Convert each Users entity to UserDTO
	                .collect(Collectors.toList()); // Return the list of UserDTOs
//	        return null;
	    }

		
}
