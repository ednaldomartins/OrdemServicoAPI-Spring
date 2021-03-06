package com.ednaldomartins.ordemservicoapi.domain.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
import com.ednaldomartins.ordemservicoapi.domain.model.StatusOrdemServico;
import com.ednaldomartins.ordemservicoapi.presentation.model.OrdemServicoInput;
import com.ednaldomartins.ordemservicoapi.presentation.model.OrdemServicoPresentation;
import com.ednaldomartins.ordemservicoapi.presentation.model.OrdemServicoResumoPresentation;

@RestController
@RequestMapping("/ordens-servico")
public class OrdemServicoController {

	@Autowired
	private CrudOrdemServico crudOrdemServico;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_ORDEM_SERVICO') and #oauth2.hasScope('read')")
	public List<OrdemServicoPresentation> listarOrdensServico(
			String nomeDoCliente,
			BigDecimal precoMenorQue,
			String status,
			String dataAberturaDe,
			Pageable pageable
	) {
		if (nomeDoCliente == null) {
			nomeDoCliente = "";
		}
		
		if (precoMenorQue == null) {
			precoMenorQue = BigDecimal.valueOf(Double.MAX_VALUE);
		}
		
		StatusOrdemServico enumStatus = convertStringToEnum(status);

		if (dataAberturaDe == null) {
			dataAberturaDe = "";
		}
		
		return enumStatus == null
				? toPresentationList(crudOrdemServico.listar(
						nomeDoCliente, 
						precoMenorQue, 
						dataAberturaDe,
						pageable
				)) 
				: toPresentationList(crudOrdemServico.listar(
						nomeDoCliente, 
						precoMenorQue, 
						enumStatus, 
						dataAberturaDe,
						pageable
				));
	}
	
	@GetMapping(path = "/{ordemServicoId}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_ORDEM_SERVICO') and #oauth2.hasScope('read')")
	public ResponseEntity<Object> buscarOrdemServico(@PathVariable Long ordemServicoId) {
		Optional<OrdemServico> ordemServico = 
				Optional.ofNullable(crudOrdemServico.buscar(ordemServicoId));
		
		return checkIsPresentAndResume(ordemServico, false);
	}

	public ResponseEntity<Object> checkIsPresentAndResume(
			Optional<OrdemServico> ordemServico,
			boolean resumo
	) {
		if (ordemServico.isPresent()) {
			Object presentationModel = resumo
					? toResumoPresentation(ordemServico.get())
					: toPresentation(ordemServico.get());		
			return ResponseEntity.ok(presentationModel);
		}
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping(path = "/{ordemServicoId}", params = "resumo")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_ORDEM_SERVICO') and #oauth2.hasScope('read')")
	public ResponseEntity<Object> buscarOrdemServicoResumo(@PathVariable Long ordemServicoId) {
		Optional<OrdemServico> ordemServico = 
				Optional.ofNullable(crudOrdemServico.buscar(ordemServicoId));
		
		return checkIsPresentAndResume(ordemServico, true);
	}
	
	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_ORDEM_SERVICO') and #oauth2.hasScope('write')")
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
	
	@PutMapping("/{ordemServicoId}/finalizar")
	@PreAuthorize("hasAuthority('ROLE_ALTERAR_ORDEM_SERVICO') and #oauth2.hasScope('write')")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void finalizarOrdemServico(@PathVariable Long ordemServicoId) {
		crudOrdemServico.finalizar(ordemServicoId);
	}
	
	@PutMapping("/{ordemServicoId}/cancelar")
	@PreAuthorize("hasAuthority('ROLE_ALTERAR_ORDEM_SERVICO') and #oauth2.hasScope('write')")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void cancelarOrdemServico(@PathVariable Long ordemServicoId) {
		crudOrdemServico.cancelar(ordemServicoId);
	}
	
	private OrdemServico toEntity(OrdemServicoInput ordemServicoInput) {
		return modelMapper.map(ordemServicoInput, OrdemServico.class);
	}
	
	private OrdemServicoPresentation toPresentation(OrdemServico ordemServico) {
		return modelMapper.map(ordemServico, OrdemServicoPresentation.class);
	}
	
	private OrdemServicoResumoPresentation toResumoPresentation(OrdemServico ordemServico) {
		return modelMapper.map(ordemServico, OrdemServicoResumoPresentation.class);
	}
	
	private List<OrdemServicoPresentation> toPresentationList(Page<OrdemServico> ordemServico) {	
		return ordemServico
				.stream()
				.map(os -> toPresentation(os))
				.collect(Collectors.toList());
	}
	
	private StatusOrdemServico convertStringToEnum(String status) {
		if(status != null) {
			if (status.toUpperCase().equals("FINALIZADA")) {
				return StatusOrdemServico.FINALIZADA;
			}
			else if (status.toUpperCase().equals("CANCELADA")) {
				return StatusOrdemServico.CANCELADA;
			} 
			else if (status.toUpperCase().equals("ABERTA")) {
				return StatusOrdemServico.ABERTA;
			}
		}
			
		return null;
	}
}
