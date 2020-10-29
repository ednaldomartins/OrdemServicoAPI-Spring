package com.ednaldomartins.ordemservicoapi.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ednaldomartins.ordemservicoapi.domain.model.Cliente;
import com.ednaldomartins.ordemservicoapi.domain.repository.ClienteRepository;

@Service
public class CrudCliente {

	@Autowired
	private ClienteRepository clienteRepository;
	
	public Cliente salvar(Cliente entity) {
		return clienteRepository.save(entity);
	}
	
	public void excluir(Long clientId) {
		clienteRepository.deleteById(clientId);
	}
}
