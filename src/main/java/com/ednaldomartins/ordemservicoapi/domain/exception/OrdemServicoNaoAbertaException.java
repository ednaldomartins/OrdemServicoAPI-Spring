package com.ednaldomartins.ordemservicoapi.domain.exception;

public class OrdemServicoNaoAbertaException extends NegocioException {

	private static final long serialVersionUID = 1L;

	public OrdemServicoNaoAbertaException(String message) {
		super(message);
	}

}
