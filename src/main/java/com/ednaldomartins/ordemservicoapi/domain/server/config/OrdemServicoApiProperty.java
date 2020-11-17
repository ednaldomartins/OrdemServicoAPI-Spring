package com.ednaldomartins.ordemservicoapi.domain.server.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("ordemservico")
public class OrdemServicoApiProperty {

	private String origemPermitida = "http://localhost:3000";
	
	private final Seguranca seguranca = new Seguranca();
	
	public Seguranca getSeguranca() {
		return seguranca;
	}
	
	public String getOrigemPermitida() {
		return origemPermitida;
	}

	public void setOrigemPermitida(String origemPermitida) {
		this.origemPermitida = origemPermitida;
	}

	public static class Seguranca {
	
		private boolean enableHttps;

		public boolean isEnableHttps() {
			return enableHttps;
		}

		public void setEnableHttps(boolean enableHttps) {
			this.enableHttps = enableHttps;
		}
		
	}
	
}
