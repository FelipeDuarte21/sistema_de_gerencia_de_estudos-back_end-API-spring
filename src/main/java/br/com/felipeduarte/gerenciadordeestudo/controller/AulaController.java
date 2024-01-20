package br.com.felipeduarte.gerenciadordeestudo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.felipeduarte.gerenciadordeestudo.service.AulaService;

@RestController
@RequestMapping(value = "/api/v1/aula")
public class AulaController {

	private AulaService service;
	
	public AulaController(AulaService service) {
		this.service = service;
	}
	
}
