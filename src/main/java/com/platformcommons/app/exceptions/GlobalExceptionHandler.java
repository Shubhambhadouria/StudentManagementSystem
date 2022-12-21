package com.platformcommons.app.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<MyErrorDetails> NoExceptionHandler(NoHandlerFoundException se, WebRequest req){
		
		MyErrorDetails myErrorDetails= new MyErrorDetails();
		myErrorDetails.setTimeStamp(LocalDateTime.now());
		myErrorDetails.setMessage(se.getMessage());
		myErrorDetails.setDetails(req.getDescription(false));
				
		return new ResponseEntity<MyErrorDetails>(myErrorDetails, HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(StudentException.class)
	public ResponseEntity<MyErrorDetails> studentExceptionHandler(StudentException se, WebRequest req){
		
		MyErrorDetails myErrorDetails = new MyErrorDetails();
		myErrorDetails.setTimeStamp(LocalDateTime.now());
		myErrorDetails.setMessage(se.getMessage());
		myErrorDetails.setDetails(req.getDescription(false));
		
		return new ResponseEntity<MyErrorDetails>(myErrorDetails,HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(CourseException.class)
	public ResponseEntity<MyErrorDetails> courseExceptionHandler(CourseException ce, WebRequest req){
		
		MyErrorDetails myErrorDetails = new MyErrorDetails();
		myErrorDetails.setTimeStamp(LocalDateTime.now());
		myErrorDetails.setMessage(ce.getMessage());
		myErrorDetails.setDetails(req.getDescription(false));
		
		return new ResponseEntity<MyErrorDetails>(myErrorDetails,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(AddressException.class)
	public ResponseEntity<MyErrorDetails> addressExceptionHandler(AddressException ae, WebRequest req){
		
		MyErrorDetails myErrorDetails = new MyErrorDetails();
		myErrorDetails.setTimeStamp(LocalDateTime.now());
		myErrorDetails.setMessage(ae.getMessage());
		myErrorDetails.setDetails(req.getDescription(false));
		
		return new ResponseEntity<MyErrorDetails>(myErrorDetails,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(AdminException.class)
	public ResponseEntity<MyErrorDetails> adminExceptionHandler(AdminException ae, WebRequest req){
		
		MyErrorDetails myErrorDetails = new MyErrorDetails();
		myErrorDetails.setTimeStamp(LocalDateTime.now());
		myErrorDetails.setMessage(ae.getMessage());
		myErrorDetails.setDetails(req.getDescription(false));
		
		return new ResponseEntity<MyErrorDetails>(myErrorDetails,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<MyErrorDetails> otherExceptionHandler(Exception ae, WebRequest req){
		
		MyErrorDetails myErrorDetails = new MyErrorDetails();
		myErrorDetails.setTimeStamp(LocalDateTime.now());
		myErrorDetails.setMessage(ae.getMessage());
		myErrorDetails.setDetails(req.getDescription(false));
		
		return new ResponseEntity<MyErrorDetails>(myErrorDetails,HttpStatus.NOT_FOUND);
	}
	
	
}
