package com.ednaldomartins.ordemservicoapi.exceptionhandler;

import java.time.LocalDateTime;

public class ApiResponseError {
	
	private Integer status;
	private LocalDateTime data;
	private String titulo;
	
	public Integer getStatus() {
		return status;
	}
	
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public LocalDateTime getData() {
		return data;
	}
	
	public void setData(LocalDateTime data) {
		this.data = data;
	}
	
	public String getTitulo() {
		return titulo;
	}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
}
