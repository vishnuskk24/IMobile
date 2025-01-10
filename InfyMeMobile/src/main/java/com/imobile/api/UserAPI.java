package com.imobile.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.imobile.dto.LoginDTO;
import com.imobile.dto.UserDTO;
import com.imobile.exception.IMobileException;
import com.imobile.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
//import javax.validation.Valid;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@Validated
@RequestMapping(value = "users")
@CrossOrigin(origins = "*",allowPrivateNetwork = "true") 
public class UserAPI {

	 @Autowired
    private  UserService userService;
	 @Autowired
	 Environment environment;

   
	 private static final Logger logger = LoggerFactory.getLogger(UserAPI.class);

	 @Operation
	 @ApiResponses
    @PostMapping(value = "/adduser")
    public ResponseEntity<String> createUser(@Valid @RequestBody UserDTO userDTO) throws IMobileException {
    	System.out.println("line 34++++++++++++++++++" + userDTO);
//    	 UserDTO userDTO=null;
    	System.out.println(userDTO +"++++++++++++++++++++++++++++++");
    	 String mobileNumber = userService.createUser(userDTO);
        return ResponseEntity.ok("User created with mobile number: " + mobileNumber);
    }
	 @Operation
	@ApiResponses
    @PostMapping(value =  "/login")
    public ResponseEntity<String> loginUser( @RequestBody LoginDTO loginDTO) throws IMobileException {
		 
		 
    	System.out.println("line 43 api class login method");
        String message = userService.loginUser(loginDTO);
        
        
        return ResponseEntity.ok(environment.getProperty(message));
    }
	 @Operation
	@ApiResponses
    @GetMapping(value = "/getProfile/{userId}")
    public ResponseEntity<UserDTO> getUserProfile(@PathVariable String userId) throws IMobileException {
        UserDTO userDTO = userService.getUserProfile(userId);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }
	 
	 @Operation
	 @ApiResponses
    @GetMapping(value = "/all")
    public ResponseEntity<List<UserDTO>> showAllUsers() throws IMobileException {
        List<UserDTO> users = userService.showAllUsers();
        return ResponseEntity.ok(users);
    }
    
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<String> handleException(Exception e) {
//        logger.error("Exception caught: ", e);
//        return ResponseEntity.status(500).body("Internal Server Error");
//    }
}
