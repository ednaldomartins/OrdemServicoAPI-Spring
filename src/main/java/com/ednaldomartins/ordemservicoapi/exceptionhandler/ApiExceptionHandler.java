package com.ednaldomartins.ordemservicoapi.exceptionhandler;

import java.time.LocalDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler{
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex,
			HttpHeaders headers,
			HttpStatus status,
			WebRequest request
			) {
		
		ApiResponseError apiResponseError = new ApiResponseError();
		apiResponseError.setStatus(status.value());
		apiResponseError.setTitulo("Um ou mais campos estão inválidos.");
		apiResponseError.setData(LocalDateTime.now());
		
		return super.handleExceptionInternal(ex, apiResponseError, headers, status, request);
	}
}
