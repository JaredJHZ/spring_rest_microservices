package com.josafhathz.app.ws.exceptions;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.josafhathz.app.ws.ui.model.response.ErrorMessage;

@ControllerAdvice
public class AppExceptionHandler {
	@ExceptionHandler(value = {Exception.class})
	public ResponseEntity<Object> handleException(Exception ex, WebRequest request) {
		
		String errorMessageDesc = ex.getLocalizedMessage();
		
		if(errorMessageDesc == null) errorMessageDesc = ex.toString();
		
		ErrorMessage errorMessage = new ErrorMessage(new Date() , errorMessageDesc);
		
		return new ResponseEntity<>(
				errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
		
		
	}
	
	@ExceptionHandler(value = {NullPointerException.class, UserServiceException.class})
	public ResponseEntity<Object> handleSpecificExceptions(Exception ex, WebRequest request) {
		
		String errorMessageDesc = "Null pointer Exception";
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		
		boolean isUserException = ex instanceof UserServiceException;
		
		if (isUserException) {
			errorMessageDesc = "User service exception";
			status = HttpStatus.NOT_FOUND;
		}
		
		ErrorMessage errorMessage = new ErrorMessage(new Date() , errorMessageDesc);
		
		return new ResponseEntity<>(
				errorMessage, new HttpHeaders(), status);
		
		
	}
}
