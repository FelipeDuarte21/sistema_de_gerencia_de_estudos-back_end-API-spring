package br.com.felipeduarte.gerenciadordeestudo.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import br.com.felipeduarte.gerenciadordeestudo.dto.LoginDTO;
import br.com.felipeduarte.gerenciadordeestudo.dto.TokenDTO;
import br.com.felipeduarte.gerenciadordeestudo.security.JWTUtil;
import br.com.felipeduarte.gerenciadordeestudo.security.UsuarioDetalhe;
import br.com.felipeduarte.gerenciadordeestudo.service.exceptions.IllegalParameterException;

@Service
public class AuthService {
	
	private JWTUtil jwtUtil;
	private AuthenticationManager authManager;
	
	public AuthService(JWTUtil jwtUtil,AuthenticationManager authManager) {
		this.jwtUtil = jwtUtil;
		this.authManager = authManager;
	}
	
	public TokenDTO login(LoginDTO login) {
		
		UsernamePasswordAuthenticationToken dadosLogin = login.converter();
		
		try {
			
			Authentication authentication = authManager.authenticate(dadosLogin);
			
			UsuarioDetalhe usuario = (UsuarioDetalhe) authentication.getPrincipal();
			
			String token = this.jwtUtil.geradorToken(usuario);
			
			return new TokenDTO(token, "Bearer",usuario.getId());
			
		}catch(AuthenticationException ex) {
			
			throw new IllegalParameterException("Erro! email e/ou senha incorretos!");
			
		}
		
	}
	
}
