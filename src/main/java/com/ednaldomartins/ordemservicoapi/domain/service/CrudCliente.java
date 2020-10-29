package com.ednaldomartins.ordemservicoapi.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ednaldomartins.ordemservicoapi.domain.exception.NegocioException;
import com.ednaldomartins.ordemservicoapi.domain.model.Cliente;
import com.ednaldomartins.ordemservicoapi.domain.repository.ClienteRepository;

@Service
public class CrudCliente {

	@Autowired
	private ClienteRepository clienteRepository;
	
	public Cliente salvar(Cliente cliente) {
		Cliente clienteEncontrado = clienteRepository.findByEmail(cliente.getEmail());
		
		if(clienteEncontrado != null && !clienteEncontrado.equals(cliente)) {
			throw new NegocioException("Já existe um cliente cadastrado com esse e-mail.");
		}
		
		return clienteRepository.save(cliente);
	}
	
	public void excluir(Long clientId) {
		clienteRepository.deleteById(clientId);
	}
}
