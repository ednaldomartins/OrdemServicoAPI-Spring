package com.ednaldomartins.ordemservicoapi.presentation.exceptionhandler;

import java.time.OffsetDateTime;
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

import com.ednaldomartins.ordemservicoapi.domain.exception.EntidadeNaoEncontradaException;
import com.ednaldomartins.ordemservicoapi.domain.exception.NegocioException;
import com.ednaldomartins.ordemservicoapi.domain.exception.OrdemServicoNaoAbertaException;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<Object> handleNegocio(NegocioException exception, WebRequest request) {
		return handleException(exception, request, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	public ResponseEntity<Object> handleEntidadeNaoEncontradaException(NegocioException exception, WebRequest request) {
		return handleException(exception, request, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(OrdemServicoNaoAbertaException.class)
	public ResponseEntity<Object> handlerOrdemServicoNaoAbertaException(
			OrdemServicoNaoAbertaException exception, 
			WebRequest request
			) {
		return handleException(exception, request, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<Object> handleException(NegocioException exception, WebRequest request, HttpStatus status) {
		ApiResponseError apiResponseError = new ApiResponseError(
				status.value(),
				OffsetDateTime.now(),
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
				OffsetDateTime.now(),
				"Um ou mais campos estão inválidos.",
				campos
				);
		
		return super.handleExceptionInternal(ex, apiResponseError, headers, status, request);
	}
}
