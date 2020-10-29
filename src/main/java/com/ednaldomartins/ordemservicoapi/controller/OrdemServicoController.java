package com.ednaldomartins.ordemservicoapi.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ednaldomartins.ordemservicoapi.domain.model.OrdemServico;
import com.ednaldomartins.ordemservicoapi.domain.repository.OrdemServicoRepository;
import com.ednaldomartins.ordemservicoapi.domain.service.CrudOrdemServico;

@RestController
@RequestMapping("/ordens-servico")
public class OrdemServicoController {

	@Autowired
	private CrudOrdemServico crudOrdemServico;
	
	@Autowired
	private OrdemServicoRepository OrdemServicoRepository;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public OrdemServico criar(@Valid @RequestBody OrdemServico ordemServico) {
		return crudOrdemServico.criar(ordemServico);
	}
	
	@GetMapping
	public List<OrdemServico> listar() {
		return OrdemServicoRepository.findAll();
	}
	
	@GetMapping("/{ordemServicoId}")
	public ResponseEntity<OrdemServico> buscar(@PathVariable Long ordemServicoId) {
		Optional<OrdemServico> ordemservico = OrdemServicoRepository.findById(ordemServicoId);
		
		if (ordemservico.isPresent()) {
			return ResponseEntity.ok(ordemservico.get());
		}
		
		return ResponseEntity.notFound().build();
	}
}
