package com.ednaldomartins.ordemservicoapi.domain.server.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("ordemservico")
public class OrdemServicoApiProperty {

	private final Seguranca seguranca = new Seguranca();
	
	public Seguranca getSeguranca() {
		return seguranca;
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
