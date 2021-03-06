package com.ednaldomartins.ordemservicoapi.domain.server.token;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.ednaldomartins.ordemservicoapi.domain.server.config.OrdemServicoApiProperty;

@ControllerAdvice
public class RefreshTokenPostProcessor implements ResponseBodyAdvice<OAuth2AccessToken>{

	@Autowired
	private OrdemServicoApiProperty ordemServicoApiProperty;
	
	@Override
	public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
		return returnType.getMethod().getName().equals("postAccessToken");
	}

	@Override
	public OAuth2AccessToken beforeBodyWrite(
			OAuth2AccessToken body,
			MethodParameter returnType,
			MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType,
			ServerHttpRequest request, ServerHttpResponse response
	) {
		String refreshToken = body.getRefreshToken().getValue();
		HttpServletRequest servletRequest = ((ServletServerHttpRequest) request).getServletRequest();
		HttpServletResponse servletResponse = ((ServletServerHttpResponse) response).getServletResponse();
		
		//	guardando refresh token no cookie
		Cookie cookie = new Cookie("refreshToken", refreshToken);
		cookie.setHttpOnly(true);
		cookie.setSecure(ordemServicoApiProperty.getSeguranca().isEnableHttps()); // http -> usar https em producao
		cookie.setPath(servletRequest.getContextPath() + "/oauth/token");
		cookie.setMaxAge(60*60*24*30);
		servletResponse.addCookie(cookie);
		
		//	apagando refresh token do body
		DefaultOAuth2AccessToken token = (DefaultOAuth2AccessToken) body;
		token.setRefreshToken(null);
		
		return body;
	}

}
