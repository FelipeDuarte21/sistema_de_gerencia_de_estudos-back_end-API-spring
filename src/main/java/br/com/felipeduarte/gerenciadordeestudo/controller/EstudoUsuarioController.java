package br.com.felipeduarte.gerenciadordeestudo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.felipeduarte.gerenciadordeestudo.service.EstudoUsuarioService;

@RestController
@RequestMapping(value =  "/api/v1/estudo-usuario")
public class EstudoUsuarioController {

	private EstudoUsuarioService service;
	
	public EstudoUsuarioController(EstudoUsuarioService service) {
		this.service = service;
	}
	
}
