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

import br.com.felipeduarte.gerenciadordeestudo.controller.exceptions.ObjectBadRequestException;
import br.com.felipeduarte.gerenciadordeestudo.controller.exceptions.ObjectNotFoundException;
import br.com.felipeduarte.gerenciadordeestudo.dto.AulaDTO;
import br.com.felipeduarte.gerenciadordeestudo.dto.AulaDadosDTO;
import br.com.felipeduarte.gerenciadordeestudo.service.AulaService;
import br.com.felipeduarte.gerenciadordeestudo.service.exceptions.IllegalParameterException;
import br.com.felipeduarte.gerenciadordeestudo.service.exceptions.ObjectNotFoundFromParameterException;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/api/v1/aula")
public class AulaController {

	private AulaService service;
	
	public AulaController(AulaService service) {
		this.service = service;
	}
	
	@PreAuthorize("hasAnyRole('USER')")
	@PostMapping
	public ResponseEntity<AulaDTO> cadastrar(@RequestBody @Valid AulaDadosDTO dados, UriComponentsBuilder uriBuilder){
		
		try {
			
			AulaDTO aula = this.service.cadastrar(dados);
			
			URI uri = uriBuilder.path("api/v1/aula/{id}").buildAndExpand(aula.getId()).toUri();
			
			return ResponseEntity.created(uri).body(aula);
			
		}catch(IllegalParameterException ex) {
			throw new ObjectBadRequestException(ex.getMessage());
		}
		
	}
	
	@PreAuthorize("hasAnyRole('USER')")
	@PutMapping("/{id}")
	public ResponseEntity<AulaDTO> alterar(@PathVariable(name = "id") Long idAula, 
			@RequestBody @Valid AulaDadosDTO dados){
		
		try {
			
			AulaDTO aula = this.service.alterar(idAula,dados);
			
			return ResponseEntity.ok(aula);
			
		}catch(ObjectNotFoundFromParameterException ex) {
			throw new ObjectNotFoundException(ex.getMessage());
		}
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<AulaDTO> buscarPorId(@PathVariable(name = "id") Long idAula){
		
		try {
			
			AulaDTO aula = this.service.buscarPorId(idAula);
			
			return ResponseEntity.ok(aula);
			
		}catch(ObjectNotFoundFromParameterException ex) {
			throw new ObjectNotFoundException(ex.getMessage());
		}
		
	}
	
	@PreAuthorize("hasAnyRole('USER')")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> excluir(@PathVariable(name = "id") Long idAula){
		
		try {
			
			this.service.excluir(idAula);
			
			return ResponseEntity.ok().build();
			
		}catch(ObjectNotFoundFromParameterException ex) {
			throw new ObjectNotFoundException(ex.getMessage());
		}
	}
	
	@GetMapping("/estudo-usuario/{id}")
	public ResponseEntity<Page<AulaDTO>> listar(
			@PathVariable(name = "id") Long idEstudoUsuario,
			@PageableDefault(page = 0, size = 5, direction = Direction.ASC, sort = "data") Pageable paginacao){
		
		try {
			
			Page<AulaDTO> pagina = this.service.listar(idEstudoUsuario, paginacao);
		
			return ResponseEntity.ok(pagina);
			
		}catch(ObjectNotFoundFromParameterException ex) {
			throw new ObjectNotFoundException(ex.getMessage());
		}
		
	}
	
}
