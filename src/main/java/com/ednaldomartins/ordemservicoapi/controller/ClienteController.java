package com.ednaldomartins.ordemservicoapi.controller;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ednaldomartins.ordemservicoapi.domain.model.Cliente;
import com.ednaldomartins.ordemservicoapi.domain.repository.ClienteRepository;

@RestController
@RequestMapping("/clientes")
public class ClienteController { 
	
	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@GetMapping()
	public List<Cliente> Listar() {
		return clienteRepository.findAll();
	}
	
	@GetMapping("/{clienteId}")
	public ResponseEntity<Cliente> buscarCliente(@PathVariable Long clienteId) {
		Optional<Cliente> cliente = clienteRepository.findById(clienteId);
		
		if(cliente.isPresent()) {
			return ResponseEntity.ok(cliente.get());
		}
		
		return ResponseEntity.notFound().build();
	}
}
