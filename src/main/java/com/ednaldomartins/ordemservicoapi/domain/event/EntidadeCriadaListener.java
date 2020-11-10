package com.ednaldomartins.ordemservicoapi.domain.event;

import java.net.URI;

import org.springframework.context.ApplicationListener;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class EntidadeCriadaListener implements ApplicationListener<EntidadeCriadaEvent> {

	@Override
	public void onApplicationEvent(EntidadeCriadaEvent event) {
		// retorno do header
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequestUri()
				.path("/{ordemServicoPresentationId}")
				.buildAndExpand(event.getId())
				.toUri();
		
		event.getResponse().setHeader("Location", uri.toASCIIString());
	}

}
