package com.imobile.util;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class ErrorInfo {
    private String errorMessage;
    private int statusCode;
    private LocalDateTime dateTime;

    
    // Constructor
    public ErrorInfo(String errorMessage, int statusCode, LocalDateTime dateTime) {
        this.errorMessage = errorMessage;
        
        this.statusCode = statusCode;
        this.dateTime = dateTime;
    }

	public ErrorInfo() {
		// TODO Auto-generated constructor stub
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}
    

    // Getters and setters
}
