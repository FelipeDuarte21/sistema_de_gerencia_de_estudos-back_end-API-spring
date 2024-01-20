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
import br.com.felipeduarte.gerenciadordeestudo.dto.EstudoDTO;
import br.com.felipeduarte.gerenciadordeestudo.dto.EstudoDadosDTO;
import br.com.felipeduarte.gerenciadordeestudo.service.EstudoService;
import br.com.felipeduarte.gerenciadordeestudo.service.exceptions.ObjectNotFoundFromParameterException;

@RestController
@RequestMapping(value =  "/api/v1/estudo")
public class EstudoController {

	private EstudoService service;
	
	public EstudoController(EstudoService service) {
		this.service = service;
	}
	
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
			
			EstudoDTO estudo = this.service.alterar(idEstudo, dados);
			
			return ResponseEntity.ok(estudo);
			
		}catch(ObjectNotFoundFromParameterException ex) {
			throw new ObjectNotFoundException(ex.getMessage());
		}
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<EstudoDTO> buscarPorId(@PathVariable(name = "id") Long idEstudo){
		
		try {
			
			EstudoDTO estudo = this.service.buscarPorId(idEstudo);
			
			return ResponseEntity.ok(estudo);
			
		}catch(ObjectNotFoundFromParameterException ex) {
			throw new ObjectNotFoundException(ex.getMessage());
		}
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> excluir(@PathVariable(name = "id") Long idEstudo){
		
		try {
			
			this.service.excluir(idEstudo);
			
			return ResponseEntity.ok().build();
			
		}catch(ObjectNotFoundFromParameterException ex) {
			throw new ObjectNotFoundException(ex.getMessage());
		}
		
	}
	
	@GetMapping
	public ResponseEntity<Page<EstudoDTO>> listar(
			@PageableDefault(page = 0, size = 5, direction = Direction.ASC, sort = "nome") Pageable paginacao){
		
		Page<EstudoDTO> pagina = this.service.listar(paginacao);
		
		return ResponseEntity.ok(pagina);
		
	}
 	
}
