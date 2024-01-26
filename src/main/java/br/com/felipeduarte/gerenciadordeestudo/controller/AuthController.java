package br.com.felipeduarte.gerenciadordeestudo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.felipeduarte.gerenciadordeestudo.controller.exceptions.ObjectBadRequestException;
import br.com.felipeduarte.gerenciadordeestudo.dto.LoginDTO;
import br.com.felipeduarte.gerenciadordeestudo.dto.TokenDTO;
import br.com.felipeduarte.gerenciadordeestudo.service.AuthService;
import br.com.felipeduarte.gerenciadordeestudo.service.exceptions.IllegalParameterException;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

	private AuthService authService;

	public AuthController(AuthService authService) {
		this.authService = authService;
	}
	
	@PostMapping("/login")
	public ResponseEntity<TokenDTO> login(@RequestBody @Valid LoginDTO login){
		
		try {
			
			TokenDTO token = this.authService.login(login);
			
			return ResponseEntity.ok(token);
			
		}catch(IllegalParameterException ex) {
			throw new ObjectBadRequestException(ex.getMessage());
			
		}
		
	}

}
