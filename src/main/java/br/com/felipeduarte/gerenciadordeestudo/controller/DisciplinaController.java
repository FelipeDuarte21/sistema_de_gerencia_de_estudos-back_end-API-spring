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
import br.com.felipeduarte.gerenciadordeestudo.dto.DisciplinaDTO;
import br.com.felipeduarte.gerenciadordeestudo.dto.DisciplinaDadosDTO;
import br.com.felipeduarte.gerenciadordeestudo.service.DisciplinaService;
import br.com.felipeduarte.gerenciadordeestudo.service.exceptions.ObjectNotFoundFromParameterException;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value =  "/api/v1/disciplina")
public class DisciplinaController {

	private DisciplinaService service;
	
	public DisciplinaController(DisciplinaService service) {
		this.service = service;
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping("/estudo/{idEstudo}")
	public ResponseEntity<DisciplinaDTO> cadastrar(
			@PathVariable(name = "idEstudo") Long idEstudo, 
			@RequestBody @Valid DisciplinaDadosDTO dados, UriComponentsBuilder uriBuilder){
		
		try {
		
			DisciplinaDTO disciplina = this.service.cadastrar(idEstudo, dados);
			
			URI uri = uriBuilder.path("api/v1/disciplina/estudo/{idEstudo}/{id}").buildAndExpand(idEstudo, disciplina.getId()).toUri();
	
	        return ResponseEntity.created(uri).body(disciplina);
        
		}catch(ObjectNotFoundFromParameterException ex) {
			throw new ObjectNotFoundException(ex.getMessage());
		}
		
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@PutMapping("/{id}")
	public ResponseEntity<DisciplinaDTO> alterar(@PathVariable(name = "id") Long idDisciplina, 
			@RequestBody @Valid DisciplinaDadosDTO dados){
		
		try {
			
			DisciplinaDTO disciplina = this.service.alterar(idDisciplina, dados);
			
			return ResponseEntity.ok(disciplina);
			
		}catch(ObjectNotFoundFromParameterException ex) {
			throw new ObjectNotFoundException(ex.getMessage());
		}
	
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping("/{id}")
	public ResponseEntity<DisciplinaDTO> buscarPorId(@PathVariable(name = "id") Long idDisciplina) {
		
		try {
			
			DisciplinaDTO disciplina = this.service.buscarPorId(idDisciplina);
			
			return ResponseEntity.ok(disciplina);
			
		}catch(ObjectNotFoundFromParameterException ex) {
			throw new ObjectNotFoundException(ex.getMessage());
		}
		
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> excluir(@PathVariable(name = "id") Long idDisciplina){
		
		try {
			
			this.service.excluir(idDisciplina);
			
			return ResponseEntity.ok().build();
			
		}catch(ObjectNotFoundFromParameterException ex) {
			throw new ObjectNotFoundException(ex.getMessage());
		}
		
	}
	
	@GetMapping("/estudo/{idEstudo}")
	public ResponseEntity<Page<DisciplinaDTO>> listar(
			@PathVariable(name = "idEstudo") Long idEstudo,
			@PageableDefault(page = 0, size = 5, direction = Direction.ASC, sort = "nome") Pageable paginacao){
		
		try {
		
			Page<DisciplinaDTO> pagina = this.service.listar(idEstudo, paginacao);
			
			return ResponseEntity.ok(pagina);
		
		} catch(ObjectNotFoundFromParameterException ex) {
			throw new ObjectNotFoundException(ex.getMessage());
		}
		
	}
	
}
