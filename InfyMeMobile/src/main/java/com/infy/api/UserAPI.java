package com.infy.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.infy.dto.LoginDTO;
import com.infy.dto.UserDTO;
import com.infy.exception.InfyMeMobileException;
import com.infy.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
//import javax.validation.Valid;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@Validated
@RequestMapping(value = "users")
@CrossOrigin(origins = "*") 
public class UserAPI {

	 @Autowired
    private  UserService userService;

   
	 private static final Logger logger = LoggerFactory.getLogger(UserAPI.class);

	 @Operation
	 @ApiResponses
    @PostMapping(value = "/adduser")
    public ResponseEntity<String> createUser(@Valid @RequestBody UserDTO userDTO) throws InfyMeMobileException {
    	System.out.println("line 34++++++++++++++++++" + userDTO);
//    	 UserDTO userDTO=null;
    	System.out.println(userDTO +"++++++++++++++++++++++++++++++");
    	 String mobileNumber = userService.createUser(userDTO);
        return ResponseEntity.ok("User created with mobile number: " + mobileNumber);
    }
	 @Operation
	@ApiResponses
    @PostMapping(value =  "/login")
    public ResponseEntity<Boolean> loginUser(@Valid @RequestBody LoginDTO loginDTO) throws InfyMeMobileException {
    	System.out.println("line 43 api class login method");
        boolean loggedIn = userService.loginUser(loginDTO);
        return ResponseEntity.ok(loggedIn);
    }
	 @Operation
	@ApiResponses
    @GetMapping(value = "/{userId}")
    public ResponseEntity<UserDTO> getUserProfile(@PathVariable String userId) throws InfyMeMobileException {
        UserDTO userDTO = userService.getUserProfile(userId);
        return ResponseEntity.ok(userDTO);
    }
	 @Operation
	 @ApiResponses
    @GetMapping(value = "/all")
    public ResponseEntity<List<UserDTO>> showAllUsers() throws InfyMeMobileException {
        List<UserDTO> users = userService.showAllUsers();
        return ResponseEntity.ok(users);
    }
    
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<String> handleException(Exception e) {
//        logger.error("Exception caught: ", e);
//        return ResponseEntity.status(500).body("Internal Server Error");
//    }
}
