package com.ednaldomartins.ordemservicoapi.domain.controller;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ednaldomartins.ordemservicoapi.domain.model.Cliente;
import com.ednaldomartins.ordemservicoapi.data.service.CrudCliente;

@RestController
@RequestMapping("/clientes")
public class ClienteController { 
	
	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private CrudCliente crudCliente;
	
	@GetMapping
	public List<Cliente> listarClientes() {
		return crudCliente.listar();
	}
	
	@GetMapping("/{clienteId}")
	public ResponseEntity<Cliente> buscarCliente(@PathVariable Long clienteId) {
		Optional<Cliente> cliente = crudCliente.buscar(clienteId);
		
		return cliente.isPresent()
				? ResponseEntity.ok(cliente.get())
				: ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente adicionar(@Valid @RequestBody Cliente cliente) {
		return crudCliente.salvar(cliente);
	}
	
	@PutMapping("/{clienteId}")
	public ResponseEntity<Cliente> atualizar(
			@PathVariable Long clienteId, 
			@Valid @RequestBody Cliente cliente
			) {
		
		if (!crudCliente.existe(clienteId)) {
			return ResponseEntity.notFound().build();
		}
		
		cliente.setId(clienteId);
		cliente = crudCliente.salvar(cliente);
		
		return ResponseEntity.ok(cliente);
	}
	
	@DeleteMapping("/{clienteId}")
	public ResponseEntity<Void> excluir(@PathVariable Long clienteId) {
		if (!crudCliente.existe(clienteId)) {
			return ResponseEntity.notFound().build();
		}
		
		crudCliente.excluir(clienteId);
		
		return ResponseEntity.noContent().build();
	}
}
