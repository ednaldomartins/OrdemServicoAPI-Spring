package com.ednaldomartins.ordemservicoapi.domain.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ednaldomartins.ordemservicoapi.data.service.CrudOrdemServico;
import com.ednaldomartins.ordemservicoapi.domain.model.Comentario;
import com.ednaldomartins.ordemservicoapi.domain.model.OrdemServico;
import com.ednaldomartins.ordemservicoapi.presentation.model.ComentarioInput;
import com.ednaldomartins.ordemservicoapi.presentation.model.ComentarioPresentation;

@RestController
@RequestMapping("/ordens-servico/{ordemServicoId}/comentarios")
public class ComentarioController {

	@Autowired
	private CrudOrdemServico crudOrdemServico;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_COMENTARIO') and #oauth2.hasScope('read')")
	public List<ComentarioPresentation> listar(@PathVariable Long ordemServicoId) {
		Optional<OrdemServico> ordemServico = Optional.ofNullable(crudOrdemServico.buscar(ordemServicoId));
		
		return toPresentationList(ordemServico.get().getComentarios());
	}

	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_COMENTARIO') and #oauth2.hasScope('write')")
	@ResponseStatus(HttpStatus.CREATED)
	public ComentarioPresentation adicionar(
			@PathVariable Long ordemServicoId,
			@Valid @RequestBody ComentarioInput comentarioInput
	) {
		Comentario comentario = crudOrdemServico.adicionarComentario(
				ordemServicoId, 
				comentarioInput.getDescricao()
				);
		
		return toPresentation(comentario);
	}
	
	private ComentarioPresentation toPresentation(Comentario comentario) {
		return modelMapper.map(comentario, ComentarioPresentation.class);
	}
	
	private List<ComentarioPresentation> toPresentationList(List<Comentario> comentarios) {
		return comentarios
				.stream()
				.map(comentario -> toPresentation(comentario))
				.collect(Collectors.toList());
	}
	
}
