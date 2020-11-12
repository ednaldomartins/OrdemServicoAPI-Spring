package com.ednaldomartins.ordemservicoapi.data.repository;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ednaldomartins.ordemservicoapi.domain.model.OrdemServico;
import com.ednaldomartins.ordemservicoapi.domain.model.StatusOrdemServico;

@Repository
public interface OrdemServicoRepository extends JpaRepository<OrdemServico, Long> {
//AndDataAberturaGreaterThanEqual
	List<OrdemServico> findByClienteNomeContainingAndPrecoLessThanAndStatusLike(
			String nome, 
			BigDecimal preco, 
			StatusOrdemServico status//,
//			OffsetDateTime dataAbertura
	);

	List<OrdemServico> findByClienteNomeContainingAndPrecoLessThan(
			String nome, 
			BigDecimal preco
	);

}
