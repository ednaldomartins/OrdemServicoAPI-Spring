package com.ednaldomartins.ordemservicoapi.presentation.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class ApiResponseError {
	
	private Integer status;
	private OffsetDateTime data;
	private String titulo;
	
	private List<Field> campos;
	
	public ApiResponseError(Integer status, OffsetDateTime data, String titulo) {
		super();
		this.status = status;
		this.data = data;
		this.titulo = titulo;
	}
	
	public ApiResponseError(Integer status, OffsetDateTime data, String titulo, List<Field> campos) {
		this(status, data, titulo);
		this.campos = campos;
	}
	

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
	
	public OffsetDateTime getData() {
		return data;
	}
	
	public void setData(OffsetDateTime data) {
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
