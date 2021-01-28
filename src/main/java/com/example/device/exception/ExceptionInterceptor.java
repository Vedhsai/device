package com.example.device.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionInterceptor extends ResponseEntityExceptionHandler {

  @ExceptionHandler(Exception.class)
  public final ResponseEntity<Object> handleAllExceptions(DeviceException ex) {
    DeviceExceptionSchema exceptionResponse =
        new DeviceExceptionSchema(
            ex.getResourceKey(), ex.getErrorCode(),  ex.getMessage());
    return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
  }
  
  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
      List<String> details = new ArrayList<>();
      for(ObjectError exceptionResponse : ex.getBindingResult().getAllErrors()) {
          details.add(exceptionResponse.getDefaultMessage());
      }
      
      for(String a :details) {
    	  if(a.equalsIgnoreCase("ER003")) {
    		  DeviceExceptionSchema error = new DeviceExceptionSchema("serial.number.invalid","ER003", "The serial number entered can include a - z, A - Z, 0 - 9 and hyphen. Please correct your entry.");
    	      return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    	  }
    	  else if(a.equalsIgnoreCase("ER001")) {
    		  DeviceExceptionSchema error = new DeviceExceptionSchema("machine.code.invalid","ER001", "The machine code is incorrect. Check the Machine code you provided and try again.");
    	      return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    	  }
      }
    	  
      DeviceExceptionSchema error = new DeviceExceptionSchema("serial.machine.invalid","ER005", "machine and serialnumber are invalid ,Please try again");
      return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    	  
      
  }
  
  @ExceptionHandler(DeviceException.class)
  public final ResponseEntity<Object> handleUserNotFoundException(DeviceException ex, WebRequest request) {
     
      DeviceExceptionSchema error = new DeviceExceptionSchema(ex.getResourceKey(),ex.getErrorCode(), ex.getMessage());
      return new ResponseEntity(error, HttpStatus.NOT_FOUND);
  }
}