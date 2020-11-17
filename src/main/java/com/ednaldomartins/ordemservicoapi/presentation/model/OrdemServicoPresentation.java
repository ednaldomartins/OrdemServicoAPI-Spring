package com.ednaldomartins.ordemservicoapi.presentation.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

import com.ednaldomartins.ordemservicoapi.domain.model.StatusOrdemServico;

public class OrdemServicoPresentation {

	private Long id;
	private ClienteNomePresentation cliente;
	private String descricao;
	private BigDecimal preco;
	private StatusOrdemServico statusOrdemServico;
	private OffsetDateTime dataAbertura;
	private OffsetDateTime dataFinalizacao;
	
	private List<ComentarioWithoutIdPresentation> comentarios;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public ClienteNomePresentation getCliente() {
		return cliente;
	}

	public void setCliente(ClienteNomePresentation cliente) {
		this.cliente = cliente;
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

	public List<ComentarioWithoutIdPresentation> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<ComentarioWithoutIdPresentation> comentarios) {
		this.comentarios = comentarios;
	}
	
}
