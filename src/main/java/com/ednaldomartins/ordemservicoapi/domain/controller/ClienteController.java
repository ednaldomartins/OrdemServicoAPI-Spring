package com.ednaldomartins.ordemservicoapi.domain.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
	public ResponseEntity<Cliente> adicionar(
			@Valid @RequestBody Cliente cliente,
			HttpServletResponse response
			) {
		Cliente clienteAdicionado = crudCliente.criar(cliente);
		
		// retorno do header
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequestUri()
				.path("/{clienteId}")
				.buildAndExpand(clienteAdicionado.getId())
				.toUri();
		
		return ResponseEntity.created(uri).body(clienteAdicionado);
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
		cliente = crudCliente.criar(cliente);
		
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
