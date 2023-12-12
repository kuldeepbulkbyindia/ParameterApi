package com.parameter.Exception;

import java.util.Date;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;



@RestControllerAdvice
public class GlobleExceptionHandler {
       @ExceptionHandler(MethodArgumentNotValidException.class) 
	  public ResponseEntity<Map<String, String>> handleMethodArgsNotValidException(MethodArgumentNotValidException ex){
		    Map<String, String> resp = new HashMap<>();
		    ex.getBindingResult().getAllErrors().forEach((error)->{
		    	String fieldName =  ((FieldError) error).getField();
		    	String message = error.getDefaultMessage();
		    	resp.put(fieldName, message);
		    	
		    });
		  return new ResponseEntity<Map<String, String>>(resp,HttpStatus.BAD_REQUEST);
	  }
       @ExceptionHandler(Exception.class)
       public ResponseEntity<ErrorDetails>handleGlobalException(Exception exception,
       WebRequest webRequest){
       ErrorDetails errorDetails=new ErrorDetails(new Date(),exception.getMessage(),
       webRequest.getDescription(false));
       return new ResponseEntity<>(errorDetails,HttpStatus.INTERNAL_SERVER_ERROR);
       }
       }
        

