package com.ednaldomartins.ordemservicoapi.data.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ednaldomartins.ordemservicoapi.data.repository.ClienteRepository;
import com.ednaldomartins.ordemservicoapi.domain.exception.EntidadeNaoEncontradaException;
import com.ednaldomartins.ordemservicoapi.domain.exception.NegocioException;
import com.ednaldomartins.ordemservicoapi.domain.model.Cliente;

@Service
public class CrudCliente {

	@Autowired
	private ClienteRepository clienteRepository;
	
	public List<Cliente> listar() {
		return clienteRepository.findAll();
	}
	
	public Optional<Cliente> buscar(Long clienteId) {
		return clienteRepository.findById(clienteId);
	}
	
	public boolean existe(Long clienteId) {
		return clienteRepository.existsById(clienteId);
	}
	
	public Cliente criar(Cliente cliente) {
		Cliente clienteEncontrado = clienteRepository.findByEmail(cliente.getEmail());
		
		if(clienteEncontrado != null && !clienteEncontrado.equals(cliente)) {
			throw new NegocioException("Já existe um cliente cadastrado com esse e-mail.");
		}
		
		return clienteRepository.save(cliente);
	}

	public ResponseEntity<Void> excluir(Long clienteId) {
		if (!existe(clienteId)) {
			throw new EntidadeNaoEncontradaException("O cliente com ID = " + clienteId + " não foi encontrado.");
		}

		clienteRepository.deleteById(clienteId);
		
		return ResponseEntity.noContent().build();
	}

	public ResponseEntity<Cliente> atualizar(Long clienteId, Cliente cliente) {
		if (!existe(clienteId)) {
			throw new EntidadeNaoEncontradaException("O cliente com ID = " + clienteId + " não foi encontrado.");
		}
		
		cliente.setId(clienteId);
		cliente = criar(cliente);
		
		return ResponseEntity.ok(cliente);
	}
}
