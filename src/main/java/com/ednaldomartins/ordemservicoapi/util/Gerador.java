package com.ednaldomartins.ordemservicoapi.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Gerador {

	public static void main(String[] args) {
		BCryptPasswordEncoder e = new BCryptPasswordEncoder();
		System.out.print(e.encode("spring123"));

	}

}
