package com.ednaldomartins.ordemservicoapi.domain.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ednaldomartins.ordemservicoapi.data.service.CrudOrdemServico;
import com.ednaldomartins.ordemservicoapi.domain.event.EntidadeCriadaEvent;
import com.ednaldomartins.ordemservicoapi.domain.model.OrdemServico;
import com.ednaldomartins.ordemservicoapi.presentation.model.OrdemServicoInput;
import com.ednaldomartins.ordemservicoapi.presentation.model.OrdemServicoPresentation;

@RestController
@RequestMapping("/ordens-servico")
public class OrdemServicoController {

	@Autowired
	private CrudOrdemServico crudOrdemServico;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@PostMapping
	public  ResponseEntity<OrdemServicoPresentation> criarOrdemServico(
			@Valid @RequestBody OrdemServicoInput ordemServicoInput,
			HttpServletResponse response
			) {
		OrdemServico ordemServico = toEntity(ordemServicoInput);
		OrdemServicoPresentation ordemServicoCriada =
				toPresentation(crudOrdemServico.criar(ordemServico));
		
		publisher.publishEvent(
				new EntidadeCriadaEvent(this, response, ordemServicoCriada.getId()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(ordemServicoCriada);
	}
	
	@GetMapping
	public List<OrdemServicoPresentation> listarOrdensServico() {
		return toPresentationList(crudOrdemServico.listar());
	}
	
	@GetMapping("/{ordemServicoId}")
	public ResponseEntity<OrdemServicoPresentation> buscarOrdemServico(@PathVariable Long ordemServicoId) {
		Optional<OrdemServico> ordemServico = 
				Optional.ofNullable(crudOrdemServico.buscar(ordemServicoId));
		
		if (ordemServico.isPresent()) {
			OrdemServicoPresentation presentationModel = toPresentation(ordemServico.get());		
			return ResponseEntity.ok(presentationModel);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{ordemServicoId}/finalizacao")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void finalizarOrdemServico(@PathVariable Long ordemServicoId) {
		crudOrdemServico.finalizar(ordemServicoId);
	}
	
	private OrdemServico toEntity(OrdemServicoInput ordemServicoInput) {
		return modelMapper.map(ordemServicoInput, OrdemServico.class);
	}
	
	private OrdemServicoPresentation toPresentation(OrdemServico ordemServico) {
		return modelMapper.map(ordemServico, OrdemServicoPresentation.class);
	}
	
	private List<OrdemServicoPresentation> toPresentationList(List<OrdemServico> ordemServico) {	
		return ordemServico
				.stream()
				.map(os -> toPresentation(os))
				.collect(Collectors.toList());
	}
}
