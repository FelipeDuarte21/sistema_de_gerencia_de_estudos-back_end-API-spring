package br.com.felipeduarte.gerenciadordeestudo.controller;

import java.net.URI;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
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
import br.com.felipeduarte.gerenciadordeestudo.dto.EstudoUsuarioDTO;
import br.com.felipeduarte.gerenciadordeestudo.dto.EstudoUsuarioDadosDTO;
import br.com.felipeduarte.gerenciadordeestudo.service.EstudoUsuarioService;
import br.com.felipeduarte.gerenciadordeestudo.service.exceptions.IllegalParameterException;
import br.com.felipeduarte.gerenciadordeestudo.service.exceptions.ObjectNotFoundFromParameterException;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value =  "/api/v1/estudo-usuario")
public class EstudoUsuarioController {

	private EstudoUsuarioService service;
	
	public EstudoUsuarioController(EstudoUsuarioService service) {
		this.service = service;
	}
	
	@PostMapping
	public ResponseEntity<EstudoUsuarioDTO> cadastrar(@RequestBody @Valid EstudoUsuarioDadosDTO dados, UriComponentsBuilder uriBuilder){
		
		try {
			
			EstudoUsuarioDTO estudoUsuario = this.service.cadastrar(dados);
			
			URI uri = uriBuilder.path("api/v1/estudo-usuario/{id}").buildAndExpand(estudoUsuario.getId()).toUri();
			
			return ResponseEntity.created(uri).body(estudoUsuario);
			
		}catch(IllegalParameterException ex) {
			throw new ObjectBadRequestException(ex.getMessage());
		}
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<EstudoUsuarioDTO> alterar(@PathVariable(name = "id") Long idEstudoUsuario, 
			@RequestBody @Valid EstudoUsuarioDadosDTO dados){
		
		try {
			
			EstudoUsuarioDTO estudoUsuario = this.service.alterar(idEstudoUsuario, dados);
			
			return ResponseEntity.ok(estudoUsuario);
			
		}catch (ObjectNotFoundFromParameterException ex) {
			throw new ObjectNotFoundException(ex.getMessage());
		}
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<EstudoUsuarioDTO> buscarPorId(@PathVariable(name = "id") Long idEstudoUsuario) {
		
		try {
			
			EstudoUsuarioDTO estudoUsuario = this.service.buscarPorId(idEstudoUsuario);
			
			return ResponseEntity.ok(estudoUsuario);
			
		}catch (ObjectNotFoundFromParameterException ex) {
			throw new ObjectNotFoundException(ex.getMessage());
		}
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> excluir(@PathVariable(name = "id") Long idEstudoUsuario) {
		
		try {
			
			this.service.excluir(idEstudoUsuario);
			
			return ResponseEntity.ok().build();
			
		}catch (ObjectNotFoundFromParameterException ex) {
			throw new ObjectNotFoundException(ex.getMessage());
		}
		
	}
	
	@GetMapping("/usuario/{idUsuario}")
	public ResponseEntity<Page<EstudoUsuarioDTO>> listar(@PathVariable(name = "idUsuario") Long idUsuario, 
			@PageableDefault(page = 0, size = 5, direction = Direction.ASC, sort = "dataInscricao") Pageable paginacao ){
	
		try {
		
			Page<EstudoUsuarioDTO> pagina = this.service.listar(idUsuario, paginacao);
			
			return ResponseEntity.ok(pagina);
		
		}catch (ObjectNotFoundFromParameterException ex) {
			throw new ObjectNotFoundException(ex.getMessage());
		}
		
	}
	
}
