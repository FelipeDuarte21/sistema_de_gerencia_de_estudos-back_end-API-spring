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

import br.com.felipeduarte.gerenciadordeestudo.controller.exceptions.ObjectNotFoundException;
import br.com.felipeduarte.gerenciadordeestudo.dto.UsuarioDTO;
import br.com.felipeduarte.gerenciadordeestudo.dto.UsuarioDadosDTO;
import br.com.felipeduarte.gerenciadordeestudo.service.UsuarioService;
import br.com.felipeduarte.gerenciadordeestudo.service.exceptions.ObjectNotFoundFromParameterException;

@RestController
@RequestMapping(value = "/api/v1/usuario")
public class UsuarioController {

	private UsuarioService service;
	
	public UsuarioController(UsuarioService service) {
		this.service = service;
	}
	
	@PostMapping
	public ResponseEntity<UsuarioDTO> cadastrar(@RequestBody UsuarioDadosDTO dados, UriComponentsBuilder uriBuilder){
		
		UsuarioDTO usuario = this.service.cadastrar(dados);
		
		URI uri = uriBuilder.path("api/v1/usuario/{id}").buildAndExpand(usuario.getId()).toUri();
		
        return ResponseEntity.created(uri).body(usuario);
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<UsuarioDTO> alterar(@PathVariable(name = "id") Long idUsuario, 
			@RequestBody UsuarioDadosDTO dados){
		
		try {
			
			UsuarioDTO usuario = this.service.alterar(idUsuario, dados);
			
			return ResponseEntity.ok(usuario);
			
		}catch(ObjectNotFoundFromParameterException ex) {
			throw new ObjectNotFoundException(ex.getMessage());
		}
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UsuarioDTO> buscarPorId(@PathVariable(name = "id") Long idUsuario) {
		
		try {
			
			UsuarioDTO usuario = this.service.buscarPorId(idUsuario);
			
			return ResponseEntity.ok(usuario);
			
		}catch(ObjectNotFoundFromParameterException ex) {
			throw new ObjectNotFoundException(ex.getMessage());
		}
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> excluir(@PathVariable(name = "id") Long idUsuario) {
		
		try {
			
			this.service.excluir(idUsuario);
			
			return ResponseEntity.ok().build();
			
		}catch(ObjectNotFoundFromParameterException ex) {
			throw new ObjectNotFoundException(ex.getMessage());
		}
		
	}
	
	@GetMapping
	public ResponseEntity<Page<UsuarioDTO>> listar(
			@PageableDefault(page = 0, size = 5, direction = Direction.ASC, sort = "nome") Pageable paginacao){
		
		Page<UsuarioDTO> pagina = this.service.listar(paginacao);
		
		return ResponseEntity.ok(pagina);
		
	}
	
}
