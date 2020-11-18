package com.ednaldomartins.ordemservicoapi.domain.server.token;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import com.ednaldomartins.ordemservicoapi.domain.server.security.UsuarioLogado;

public class CustomTokenEnhancer implements TokenEnhancer {

	@Override
	public OAuth2AccessToken enhance(
			OAuth2AccessToken accessToken,
			OAuth2Authentication authentication
	) {
		UsuarioLogado usuarioLogado = (UsuarioLogado) authentication.getPrincipal();
		
		Map<String, Object> addInfo = new HashMap<>();
		addInfo.put("nome", usuarioLogado.getUsuario().getNome());
		
		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(addInfo);
		return accessToken;
	}

}
