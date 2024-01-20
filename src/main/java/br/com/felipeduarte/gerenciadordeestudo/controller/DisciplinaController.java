package br.com.felipeduarte.gerenciadordeestudo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.felipeduarte.gerenciadordeestudo.service.DisciplinaService;

@RestController
@RequestMapping(value =  "/api/v1/disciplina")
public class DisciplinaController {

	private DisciplinaService service;
	
	public DisciplinaController(DisciplinaService service) {
		this.service = service;
	}
	
}
