package com.ednaldomartins.ordemservicoapi.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
}
