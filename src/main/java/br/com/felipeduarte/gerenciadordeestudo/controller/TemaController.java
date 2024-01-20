package br.com.felipeduarte.gerenciadordeestudo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.felipeduarte.gerenciadordeestudo.service.TemaService;

@RestController
@RequestMapping(value = "/api/v1/tema")
public class TemaController {

	private TemaService service;
	
	public TemaController(TemaService service) {
		this.service = service;
	}
	
	
}
