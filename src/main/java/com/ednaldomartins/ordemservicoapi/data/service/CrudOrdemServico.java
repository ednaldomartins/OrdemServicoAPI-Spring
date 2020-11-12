package com.ednaldomartins.ordemservicoapi.data.service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ednaldomartins.ordemservicoapi.data.repository.ClienteRepository;
import com.ednaldomartins.ordemservicoapi.data.repository.ComentarioRepository;
import com.ednaldomartins.ordemservicoapi.data.repository.OrdemServicoRepository;
import com.ednaldomartins.ordemservicoapi.domain.exception.EntidadeNaoEncontradaException;
import com.ednaldomartins.ordemservicoapi.domain.exception.NegocioException;
import com.ednaldomartins.ordemservicoapi.domain.exception.OrdemServicoNaoAbertaException;
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
	
	public List<OrdemServico> listar(
			String nomeDoCliente,
			BigDecimal precoMenorQue, 
			StatusOrdemServico status, 
			String dataAberturaDe
	) {
		return ordemServicoRepository
				.findByClienteNomeContainingAndPrecoLessThanAndStatusLike(
						nomeDoCliente,
						precoMenorQue, 
						status//, 
//						dataAberturaDe
				);
	}
	
	public List<OrdemServico> listar(
			String nomeDoCliente, 
			BigDecimal precoMenorQue, 
			String dataAberturaDe
	) {
		return ordemServicoRepository
				.findByClienteNomeContainingAndPrecoLessThan(
						nomeDoCliente,
						precoMenorQue
				);
	}

	public OrdemServico buscar(Long ordemServicoId) {
		return ordemServicoRepository.findById(ordemServicoId)
				.orElseThrow(() -> new EntidadeNaoEncontradaException("Ordem de serviço não encontrada"));
	}
	
	public OrdemServico criar(OrdemServico ordemServico) {
		Cliente cliente = clienteRepository.findById(
				ordemServico.getCliente().getId()
				).orElseThrow(() -> new NegocioException("Cliente não encontrado"));
		
		ordemServico.setCliente(cliente);
		ordemServico.setStatus(StatusOrdemServico.ABERTA);
		ordemServico.setDataAbertura(OffsetDateTime.now());
		
		return ordemServicoRepository.save(ordemServico); 
	}
	
	public void finalizar(Long ordemServicoId) {
		OrdemServico ordemServico = buscar(ordemServicoId);
		ordemServico.finalizar();
		
		ordemServicoRepository.save(ordemServico);
	}
	
	public void cancelar(Long ordemServicoId) {
		OrdemServico ordemServico = buscar(ordemServicoId);
		ordemServico.cancelar();
		
		ordemServicoRepository.save(ordemServico);
	}
	
	public Comentario adicionarComentario(Long ordemServicoId, String descricao) {
		OrdemServico ordemServico = buscar(ordemServicoId);
		
		if (ordemServico.finalizadaOuCancelada()) {
			throw new OrdemServicoNaoAbertaException("O comentário não pode ser adicionado a uma ordem de serviço cancelada ou finalizada.");
		}
		
		Comentario comentario = new Comentario(
				ordemServico,
				descricao,
				OffsetDateTime.now()
				);
		
		return comentarioRepository.save(comentario);
	}

}
