package br.com.felipeduarte.gerenciadordeestudo.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.felipeduarte.gerenciadordeestudo.dto.EstudoDTO;
import br.com.felipeduarte.gerenciadordeestudo.dto.EstudoDadosDTO;
import br.com.felipeduarte.gerenciadordeestudo.service.EstudoService;

@RestController
@RequestMapping(value =  "/api/v1/estudo")
public class EstudoController {

	private EstudoService service;
	
	public EstudoController(EstudoService service) {
		this.service = service;
	}
	
	/*
	@PostMapping
	public ResponseEntity<EstudoDTO> cadastrar(@RequestBody EstudoDadosDTO dados, UriComponentsBuilder uriBuilder) {
	
		EstudoDTO estudo = this.service.cadastrar(dados);
		
		URI uri = uriBuilder.path("api/v1/estudo/{id}").buildAndExpand(estudo.getId()).toUri();

        return ResponseEntity.created(uri).body(estudo);
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<EstudoDTO> alterar(@PathVariable(name = "id") Long idEstudo, 
			@RequestBody EstudoDadosDTO dados){
		
		try {
			
		}catch(Exception ex) {
			
		}
		
	}*/
	
}
