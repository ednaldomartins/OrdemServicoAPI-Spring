package com.ednaldomartins.ordemservicoapi.data.repository;

import java.math.BigDecimal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ednaldomartins.ordemservicoapi.domain.model.OrdemServico;
import com.ednaldomartins.ordemservicoapi.domain.model.StatusOrdemServico;

@Repository
public interface OrdemServicoRepository extends JpaRepository<OrdemServico, Long> {
//AndDataAberturaGreaterThanEqual
	Page<OrdemServico> findByClienteNomeContainingAndPrecoLessThanAndStatusLike(
			String nome, 
			BigDecimal preco, 
			StatusOrdemServico status,
//			OffsetDateTime dataAbertura,
			Pageable pageable
	);

	Page<OrdemServico> findByClienteNomeContainingAndPrecoLessThan(
			String nome, 
			BigDecimal preco,
			Pageable pageable
	);

	int countByClienteId(Long clienteId);

}
