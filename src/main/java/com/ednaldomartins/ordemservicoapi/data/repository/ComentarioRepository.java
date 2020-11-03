package com.ednaldomartins.ordemservicoapi.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ednaldomartins.ordemservicoapi.domain.model.Comentario;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long>{

}
