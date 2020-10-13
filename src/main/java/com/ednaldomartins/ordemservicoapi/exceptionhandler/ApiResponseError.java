package com.ednaldomartins.ordemservicoapi.exceptionhandler;

import java.time.LocalDateTime;
import java.util.List;

public class ApiResponseError {
	
	private Integer status;
	private LocalDateTime data;
	private String titulo;
	
	private List<Field> campos;
	
	public static class Field {
		
		private String nome;
		private String mensagem;
		
		public Field(String nome, String mensagem) {
			super();
			this.nome = nome;
			this.mensagem = mensagem;
		}

		public String getNome() {
			return nome;
		}
		
		public void setNome(String nome) {
			this.nome = nome;
		}
		
		public String getMensagem() {
			return mensagem;
		}
		
		public void setMensagem(String mensagem) {
			this.mensagem = mensagem;
		}
		
	}
	
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

	public List<Field> getCampos() {
		return campos;
	}

	public void setCampos(List<Field> campos) {
		this.campos = campos;
	}
	
}
