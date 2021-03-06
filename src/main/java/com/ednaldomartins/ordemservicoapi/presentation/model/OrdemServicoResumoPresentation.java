package com.ednaldomartins.ordemservicoapi.presentation.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import com.ednaldomartins.ordemservicoapi.domain.model.StatusOrdemServico;

public class OrdemServicoResumoPresentation {

	private Long id;
	private String clienteNome;
	private String descricao;
	private BigDecimal preco;
	private StatusOrdemServico statusOrdemServico;
	private OffsetDateTime dataAbertura;
	private OffsetDateTime dataFinalizacao;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getClienteNome() {
		return clienteNome;
	}
	
	public void setClienteNome(String clienteNome) {
		this.clienteNome = clienteNome;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public BigDecimal getPreco() {
		return preco;
	}
	
	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}
	
	public StatusOrdemServico getStatusOrdemServico() {
		return statusOrdemServico;
	}
	
	public void setStatusOrdemServico(StatusOrdemServico statusOrdemServico) {
		this.statusOrdemServico = statusOrdemServico;
	}
	
	public OffsetDateTime getDataAbertura() {
		return dataAbertura;
	}
	
	public void setDataAbertura(OffsetDateTime dataAbertura) {
		this.dataAbertura = dataAbertura;
	}
	
	public OffsetDateTime getDataFinalizacao() {
		return dataFinalizacao;
	}
	
	public void setDataFinalizacao(OffsetDateTime dataFinalizacao) {
		this.dataFinalizacao = dataFinalizacao;
	}
	
}
