package com.ednaldomartins.ordemservicoapi.domain.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
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
import com.ednaldomartins.ordemservicoapi.presentation.model.OrdemServicoPresentation;
import com.ednaldomartins.ordemservicoapi.data.repository.OrdemServicoRepository;
import com.ednaldomartins.ordemservicoapi.data.service.CrudOrdemServico;

@RestController
@RequestMapping("/ordens-servico")
public class OrdemServicoController {

	@Autowired
	private CrudOrdemServico crudOrdemServico;
	
	@Autowired
	private OrdemServicoRepository OrdemServicoRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
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
	public ResponseEntity<OrdemServicoPresentation> buscar(@PathVariable Long ordemServicoId) {
		Optional<OrdemServico> ordemservico = OrdemServicoRepository.findById(ordemServicoId);
		
		if (ordemservico.isPresent()) {
			OrdemServicoPresentation presentationModel = 
				modelMapper.map(ordemservico.get(), OrdemServicoPresentation.class);
					
			return ResponseEntity.ok(presentationModel);
		}
		
		return ResponseEntity.notFound().build();
	}
}
