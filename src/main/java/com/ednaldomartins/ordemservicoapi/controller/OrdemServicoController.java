package com.ednaldomartins.ordemservicoapi.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ednaldomartins.ordemservicoapi.domain.model.OrdemServico;
import com.ednaldomartins.ordemservicoapi.domain.service.CrudOrdemServico;

@RestController
@RequestMapping("/ordens-servico")
public class OrdemServicoController {

	@Autowired
	private CrudOrdemServico crudOrdemServico;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public OrdemServico criar(@Valid @RequestBody OrdemServico ordemServico) {
		return crudOrdemServico.criar(ordemServico);
	}
}
