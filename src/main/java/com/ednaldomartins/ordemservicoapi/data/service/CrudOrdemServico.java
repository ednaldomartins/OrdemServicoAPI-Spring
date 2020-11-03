package com.ednaldomartins.ordemservicoapi.data.service;

import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ednaldomartins.ordemservicoapi.data.repository.ClienteRepository;
import com.ednaldomartins.ordemservicoapi.data.repository.ComentarioRepository;
import com.ednaldomartins.ordemservicoapi.data.repository.OrdemServicoRepository;
import com.ednaldomartins.ordemservicoapi.domain.exception.NegocioException;
import com.ednaldomartins.ordemservicoapi.domain.model.Cliente;
import com.ednaldomartins.ordemservicoapi.domain.model.Comentario;
import com.ednaldomartins.ordemservicoapi.domain.model.OrdemServico;
import com.ednaldomartins.ordemservicoapi.domain.model.StatusOrdemServico;

@Service
public class CrudOrdemServico {

	@Autowired
	private OrdemServicoRepository ordemServicoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ComentarioRepository comentarioRepository;
	
	public OrdemServico criar(OrdemServico ordemServico) {
		Cliente cliente = clienteRepository.findById(
				ordemServico.getCliente().getId()
				).orElseThrow(() -> new NegocioException("Cliente não encontrado"));
		
		ordemServico.setCliente(cliente);
		ordemServico.setStatus(StatusOrdemServico.ABERTA);
		ordemServico.setDataAbertura(OffsetDateTime.now());
		
		return ordemServicoRepository.save(ordemServico); 
	}
	
	public Comentario adicionarComentario(Long ordemServicoId, String descricao) {
		OrdemServico ordemServico = ordemServicoRepository.findById(ordemServicoId)
				.orElseThrow(() -> new NegocioException("Ordem de serviço não encontrada"));
		
		Comentario comentario = new Comentario(
				ordemServico,
				descricao,
				OffsetDateTime.now()
				);
		
		return comentarioRepository.save(comentario);
	}
}
