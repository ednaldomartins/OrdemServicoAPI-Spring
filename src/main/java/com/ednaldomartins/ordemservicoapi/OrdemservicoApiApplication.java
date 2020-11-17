package com.ednaldomartins.ordemservicoapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.ednaldomartins.ordemservicoapi.domain.server.config.OrdemServicoApiProperty;

@SpringBootApplication
@EnableConfigurationProperties(OrdemServicoApiProperty.class)
public class OrdemservicoApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrdemservicoApiApplication.class, args);
	}

}
