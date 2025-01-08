package com.infy.util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.infy.exception.IMobileException;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

//import javax.validation.ConstraintViolationException;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@Autowired
	Environment environment;
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorInfo> handleConstraintViolationException(ConstraintViolationException ex) {
ErrorInfo error =  new ErrorInfo();
    	
    	error.setStatusCode(HttpStatus.BAD_REQUEST.value());
    	error.setDateTime(LocalDateTime.now());
    	String errormsg = new String();
    	
    	for( ConstraintViolation<?> o: ex.getConstraintViolations()) {
    		if(!errormsg.equals("")) errormsg =  errormsg+","+o.getMessage();
    		else errormsg+=o.getMessage();
    		
    	}
    	error.setErrorMessage(errormsg);
    	return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorInfo> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
      
    	ErrorInfo error =  new ErrorInfo();
    	
    	error.setStatusCode(HttpStatus.BAD_REQUEST.value());
    	error.setDateTime(LocalDateTime.now());
    	String errormsg = new String();
    	
    	for( ObjectError o: ex.getBindingResult().getAllErrors()) {
    		if(!errormsg.equals("")) errormsg =  errormsg+","+o.getDefaultMessage();
    		else errormsg+=o.getDefaultMessage();
    		
    	}
    	error.setErrorMessage(errormsg);
//    System.out.println(errormsg);
    	return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IMobileException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorInfo> handleInfyMeMobileException(IMobileException ex) {
    	System.out.println(ex.getMessage());
        ErrorInfo errorInfo = new ErrorInfo(environment.getProperty(ex.getMessage()), HttpStatus.BAD_REQUEST.value(), LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorInfo);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorInfo> generalException(Exception ex) {
        ErrorInfo errorInfo = new ErrorInfo(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorInfo);
    }
    // Other exception handlers can be added as needed

}
