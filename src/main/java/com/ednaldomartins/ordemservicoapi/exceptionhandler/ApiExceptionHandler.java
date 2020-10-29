package com.ednaldomartins.ordemservicoapi.exceptionhandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ednaldomartins.ordemservicoapi.domain.exception.NegocioException;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<Object> handleNegocio(NegocioException exception, WebRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		ApiResponseError apiResponseError = new ApiResponseError(
				status.value(),
				LocalDateTime.now(),
				exception.getMessage()
				);
		
		return handleExceptionInternal(exception, apiResponseError, new HttpHeaders(), status, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex,
			HttpHeaders headers,
			HttpStatus status,
			WebRequest request
			) {
		
		List<ApiResponseError.Field> campos = new ArrayList<ApiResponseError.Field>();
		
		for(ObjectError error: ex.getBindingResult().getAllErrors()) {
			String nome = ((FieldError) error).getField();
			String mensagem = error.getDefaultMessage();
			campos.add(new ApiResponseError.Field(nome, mensagem));
		}
		
		ApiResponseError apiResponseError = new ApiResponseError(
				status.value(),
				LocalDateTime.now(),
				"Um ou mais campos estão inválidos.",
				campos
				);
		
		return super.handleExceptionInternal(ex, apiResponseError, headers, status, request);
	}
}
