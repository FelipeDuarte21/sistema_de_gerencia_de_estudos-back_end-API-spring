package br.com.felipeduarte.gerenciadordeestudo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.felipeduarte.gerenciadordeestudo.service.EstudoService;

@RestController
@RequestMapping(value =  "/api/v1/estudo")
public class EstudoController {

	private EstudoService service;
	
	public EstudoController(EstudoService service) {
		this.service = service;
	}
	
}
