package com.ednaldomartins.ordemservicoapi.controller;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ednaldomartins.ordemservicoapi.domain.model.Cliente;
import com.ednaldomartins.ordemservicoapi.domain.repository.ClienteRepository;

@RestController
public class ClienteController { 
	
	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@GetMapping("/clientes")
	public List<Cliente> Listar() {
		return clienteRepository.findAll();
	}
	
	@GetMapping("/clientes/{clienteId}")
	public Cliente buscarCliente(@PathVariable Long clienteId) {
		Optional<Cliente> cliente = clienteRepository.findById(clienteId);
		
		return cliente.orElse(null);
	}
	
	@GetMapping("/clientes/nome/{clienteNome}")
	public List<Cliente> ListarPorNome(@PathVariable String clienteNome) {
		return clienteRepository.findByNome(clienteNome);
	}
	
	@GetMapping("/clientes/parteNome/{parteNome}")
	public List<Cliente> ListarPorNomeContaining(@PathVariable String parteNome) {
		return clienteRepository.findByNomeContaining(parteNome);
	}
}
