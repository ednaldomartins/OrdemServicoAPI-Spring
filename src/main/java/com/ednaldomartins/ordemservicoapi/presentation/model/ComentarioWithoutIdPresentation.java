package com.ednaldomartins.ordemservicoapi.presentation.model;

import java.time.OffsetDateTime;

public class ComentarioWithoutIdPresentation {
	
	private String descricao;
	private OffsetDateTime dataEnvio;
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public OffsetDateTime getDataEnvio() {
		return dataEnvio;
	}
	
	public void setDataEnvio(OffsetDateTime dataEnvio) {
		this.dataEnvio = dataEnvio;
	}
}
