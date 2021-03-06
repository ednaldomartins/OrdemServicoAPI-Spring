package com.ednaldomartins.ordemservicoapi.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ednaldomartins.ordemservicoapi.domain.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{

	List<Cliente> findByNomeContainingAndEmailContainingAndEnderecoEstadoContaining(String nome, String email, String estado);
	Cliente findByEmail(String email);
	
}
