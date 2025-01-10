 package com.imobile.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.imobile.dto.LoginDTO;
import com.imobile.dto.UserDTO;
import com.imobile.entity.Users;
import com.imobile.exception.IMobileException;
import com.imobile.repository.UserRepository;
import com.imobile.util.OTPGeneratorService;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	
		@Autowired
	  private  UserRepository userRepository;
		@Autowired
		OTPGeneratorService otpGeneratorService;

	    public UserServiceImpl(UserRepository userRepository) {
	        this.userRepository = userRepository;
	    }

	    @Override
	    public String createUser(UserDTO userDTO) throws IMobileException {
	        
	            // Check if the Users already exists
	    	System.out.println("service lie 33 -> " + userDTO.toString());
	            Optional<Users> existingUser = userRepository.findByMobileNumber(userDTO.getMobileNumber());
	            if (existingUser.isPresent()) {
	                throw new IMobileException("Service.User_Already_Registered_With_Mobile_Number");
	            }
	            
	            // Save the new Users
	            Users newUser = new Users(userDTO);
	            
	            userRepository.save(newUser);
	            return newUser.getMobileNumber()+""; // Return the mobile number of the created Users
	        
	    }

	    @Override
	    public String loginUser(LoginDTO loginDTO) throws IMobileException {
	        // Find the Users by mobile number
	    	System.out.println(loginDTO + "  service line 50" + Thread.currentThread().getStackTrace()[2].getLineNumber());
	        Optional<Users> userOptional = userRepository.findByMobileNumber(loginDTO.getMobileNumber());
	        if (userOptional.isPresent()) {
	            Users Users = userOptional.get();
	            // Compare passwords
	            if (Users.getPassword().equals(loginDTO.getPassword())) {
	            	System.out.println("User logi success ful login service");
	                return "Service.User_Login_Success"; // Authentication successful
	            }
	        }
	        // Authentication failed
	        throw new IMobileException("Authentication_failed");
	    }

	    @Override
	    public UserDTO getUserProfile(String userId) throws IMobileException {
	    	System.out.println("line 65 in service");
	        Optional<Users> userOptional = userRepository.findByUserId(userId);
	        if (userOptional.isPresent()) {
	            Users Users = userOptional.get();

	            return new UserDTO(Users); // Convert Users entity to UserDTO and return

	        } else {
	            throw new IMobileException("Users_ID_not_found");
	        }
	    }

	    @Override
	    public List<UserDTO> showAllUsers() throws IMobileException {
	        List<Users> users = userRepository.findAll();
	        if (users.isEmpty()) {
	            throw new IMobileException("No_users_found");
	        }
	        return users.stream()
	                .map(x-> new UserDTO(x)) // Convert each Users entity to UserDTO
	                .collect(Collectors.toList()); // Return the list of UserDTOs
//	        return null;
	    }

		@Override
		public void checkUser(String mail) throws IMobileException {
			Optional<Users> optional = userRepository.findByEmail(mail);
			optional.orElseThrow(()-> new IMobileException("Service.User_Mail_Not_Found"));
			
		}
		@Override
		public String sendOTPByMail(String mail) throws IMobileException {
			
			checkUser(mail);
			return otpGeneratorService.generateAndStoreOTPForMail(mail);
		}
		@Override
		public String validateOTPByMail(String mail,String OTP, String pasword) throws IMobileException {
			String resp = "";
			checkUser(mail);
			if(otpGeneratorService.validateOTP(mail, OTP)) {
				userRepository.findByEmail(mail).get().setPassword(pasword);
				resp="Service.Passwod_Changed_Successfully";
				
			}else throw new IMobileException("Serice.OTP_Invalid");
			return resp;
		}
		
		

		
}
