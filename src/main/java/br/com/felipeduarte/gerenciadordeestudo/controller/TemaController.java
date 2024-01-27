package br.com.felipeduarte.gerenciadordeestudo.controller;

import java.net.URI;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.felipeduarte.gerenciadordeestudo.controller.exceptions.ObjectNotFoundException;
import br.com.felipeduarte.gerenciadordeestudo.dto.TemaDTO;
import br.com.felipeduarte.gerenciadordeestudo.dto.TemaDadosDTO;
import br.com.felipeduarte.gerenciadordeestudo.dto.TemaMetricaDTO;
import br.com.felipeduarte.gerenciadordeestudo.service.TemaService;
import br.com.felipeduarte.gerenciadordeestudo.service.exceptions.ObjectNotFoundFromParameterException;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/api/v1/tema")
public class TemaController {

	private TemaService service;
	
	public TemaController(TemaService service) {
		this.service = service;
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping("/disciplina/{idDisciplina}")
	public ResponseEntity<TemaDTO> cadastrar(
			@PathVariable(name = "idDisciplina") Long idDisciplina, 
			@RequestBody @Valid TemaDadosDTO dados, UriComponentsBuilder uriBuilder){
		
		try {
			
			TemaDTO tema = this.service.cadastrar(idDisciplina, dados);
			
			URI uri = uriBuilder.path("api/v1/tema/disciplina/{idDisciplina}/{id}").buildAndExpand(idDisciplina, tema.getId()).toUri();
			
			return ResponseEntity.created(uri).body(tema);
			
		}catch(ObjectNotFoundFromParameterException ex) {
			throw new ObjectNotFoundException(ex.getMessage());
		}
		
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@PutMapping("/{idTema}")
	public ResponseEntity<TemaDTO> alterar(@PathVariable(name = "idTema") Long idTema,
			@RequestBody @Valid TemaDadosDTO dados ){
		
		try {
			
			TemaDTO tema = this.service.alterar(idTema, dados);
			
			return ResponseEntity.ok(tema);
			
		}catch(ObjectNotFoundFromParameterException ex) {
			throw new ObjectNotFoundException(ex.getMessage());
		}
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<TemaDTO> buscarPorId(@PathVariable(name = "id") Long idTema) {
	
		try {
			
			TemaDTO tema = this.service.buscarPorId(idTema);
			
			return ResponseEntity.ok(tema);
			
		}catch(ObjectNotFoundFromParameterException ex) {
			throw new ObjectNotFoundException(ex.getMessage());
		}

	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> excluir(@PathVariable(name = "id") Long idTema) {
	
		try {
			
			this.service.excluir(idTema);
			
			return ResponseEntity.ok().build();
			
		}catch(ObjectNotFoundFromParameterException ex) {
			throw new ObjectNotFoundException(ex.getMessage());
		}

	}
	
	@GetMapping("/disciplina/{idDisciplina}")
	public ResponseEntity<Page<TemaDTO>> listar(
			@PathVariable(name = "idDisciplina") Long idDisciplina,
			@PageableDefault(page = 0, size = 5, direction = Direction.ASC, sort = "nome") Pageable paginacao){
		
		try {
			
			Page<TemaDTO> pagina = this.service.listar(idDisciplina, paginacao);
			
			return ResponseEntity.ok(pagina);
			
		}catch(ObjectNotFoundFromParameterException ex) {
			throw new ObjectNotFoundException(ex.getMessage());
		}
		
	}
	
	@GetMapping("/metrica/{id}")
	public ResponseEntity<TemaMetricaDTO> calcularMetrica(@PathVariable(name = "id") Long idTema){
		
		try {
			
			TemaMetricaDTO metrica = this.service.calcularMetrica(idTema);
			
			return ResponseEntity.ok(metrica);
			
		}catch(ObjectNotFoundFromParameterException ex) {
			throw new ObjectNotFoundException(ex.getMessage());
		}
		
	}
	
	
}
