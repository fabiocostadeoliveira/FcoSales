package com.fco.sales.resouces.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fco.sales.services.exceptions.IntegrityViolationException;
import com.fco.sales.services.exceptions.ObjectNotFoundException;
import com.fco.sales.services.exceptions.UserNotFoundException;
import com.fco.sales.services.exceptions.UserOrPassworIncorretException;


@ControllerAdvice
public class ResouceExceptionHandler {
	
	@ExceptionHandler (ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request){
		StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}
	
	@ExceptionHandler (IntegrityViolationException.class)
	public ResponseEntity<StandardError> integrityViolation(IntegrityViolationException e, HttpServletRequest request){
		StandardError err = new StandardError(HttpStatus.BAD_REQUEST.value(), e.getMessage(), System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}
	
	@ExceptionHandler (MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> validationMethodArgNotValid(MethodArgumentNotValidException e, HttpServletRequest request){
		ValidationError err = new ValidationError(HttpStatus.BAD_REQUEST.value(), "Erro de validação" , System.currentTimeMillis());
		
		e.getBindingResult().getFieldErrors() .forEach((i) -> err.addError(i.getField(), i.getDefaultMessage()));
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}
	
	@ExceptionHandler (UserNotFoundException.class)
	public ResponseEntity<StandardError> userNotFound(UserNotFoundException e, HttpServletRequest request){
		StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}
	
	@ExceptionHandler (UserOrPassworIncorretException.class)
	public ResponseEntity<StandardError> userNotFound(UserOrPassworIncorretException e, HttpServletRequest request){
		StandardError err = new StandardError(HttpStatus.UNAUTHORIZED.value(), e.getMessage(), System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(err);
	}

}
