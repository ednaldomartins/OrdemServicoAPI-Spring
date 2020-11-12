package com.ednaldomartins.ordemservicoapi.domain.controller;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ednaldomartins.ordemservicoapi.data.service.CrudCliente;
import com.ednaldomartins.ordemservicoapi.domain.event.EntidadeCriadaEvent;
import com.ednaldomartins.ordemservicoapi.domain.model.Cliente;

@RestController
@RequestMapping("/clientes")
public class ClienteController { 
	
	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private CrudCliente crudCliente;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	/*	exemplo de @GET filtrando apenas por nome, email e Endereco.estado	*/
	@GetMapping
	public List<Cliente> listarClientes(String nome, String email, String estado) {
		
		if (nome == null) {
			nome = "";
		}
		if (email == null) {
			email = "";
		}
		if (estado == null) {
			estado = "";
		}
		
		return crudCliente.listar(nome, email, estado);
	}
	
	@GetMapping("/{clienteId}")
	public ResponseEntity<Cliente> buscarCliente(@PathVariable Long clienteId) {
		Optional<Cliente> cliente = crudCliente.buscar(clienteId);
		
		return cliente.isPresent()
				? ResponseEntity.ok(cliente.get())
				: ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<Cliente> criarCliente(
			@Valid @RequestBody Cliente cliente,
			HttpServletResponse response
			) {
		Cliente clienteCriado = crudCliente.criar(cliente);
		
		publisher.publishEvent(
				new EntidadeCriadaEvent(this, response, clienteCriado.getId()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(clienteCriado);
	}
	
	@PutMapping("/{clienteId}")
	public ResponseEntity<Cliente> atualizarCliente(
			@PathVariable Long clienteId, 
			@Valid @RequestBody Cliente cliente
			) {
		
		return crudCliente.atualizar(clienteId, cliente);
	}
	
	@DeleteMapping("/{clienteId}")
	public ResponseEntity<Void> excluirCliente(@PathVariable Long clienteId) {
		return crudCliente.excluir(clienteId);
	}

}
