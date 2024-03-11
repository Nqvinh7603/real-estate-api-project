package com.laptrinhjavaweb;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.laptrinhjavaweb.customexception.FieldRequireException;
import com.laptrinhjavaweb.dto.ErrorResponseBean;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {
	@ExceptionHandler(ArithmeticException.class)
	public ResponseEntity<ErrorResponseBean> handleArithemticException(ArithmeticException ex, WebRequest request){
		ErrorResponseBean result = new ErrorResponseBean();
		result.setError(ex.getMessage());
		List<String> details = new ArrayList<>();
		details.add("10 sao chia het cho 0?");
		result.setDetails(details);
		return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	@ExceptionHandler(FieldRequireException.class)
	public ResponseEntity<ErrorResponseBean> handleFieldRequireException(FieldRequireException ex, WebRequest request){
		ErrorResponseBean result = new ErrorResponseBean();
		result.setError(ex.getMessage());
		List<String> details = new ArrayList<>();
		result.setDetails(details);
		return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
	}
}
