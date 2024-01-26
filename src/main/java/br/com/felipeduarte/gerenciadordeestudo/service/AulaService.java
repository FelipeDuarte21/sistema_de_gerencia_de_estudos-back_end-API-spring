package br.com.felipeduarte.gerenciadordeestudo.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.felipeduarte.gerenciadordeestudo.dto.AulaDTO;
import br.com.felipeduarte.gerenciadordeestudo.dto.AulaDadosDTO;
import br.com.felipeduarte.gerenciadordeestudo.entity.Aula;
import br.com.felipeduarte.gerenciadordeestudo.entity.EstudoUsuario;
import br.com.felipeduarte.gerenciadordeestudo.entity.Tema;
import br.com.felipeduarte.gerenciadordeestudo.repository.AulaRepository;
import br.com.felipeduarte.gerenciadordeestudo.service.exceptions.IllegalParameterException;
import br.com.felipeduarte.gerenciadordeestudo.service.exceptions.ObjectNotFoundFromParameterException;

@Service
public class AulaService {

	private AulaRepository repository;
	
	private EstudoUsuarioService estudoUsuarioService;
	
	private TemaService temaService;
	
	public AulaService(AulaRepository repository, EstudoUsuarioService estudoUsuarioService, 
			TemaService temaService) {
		this.repository = repository;
		this.estudoUsuarioService = estudoUsuarioService;
		this.temaService = temaService;
	}

	public AulaDTO cadastrar(AulaDadosDTO dados) {
		
		try {
			
			EstudoUsuario estudoUsuario = this.estudoUsuarioService.buscarPorId(dados.getIdEstudoUsuario(), 0);
			
			Tema tema = this.temaService.buscarPorId(dados.getIdTema(), 0);
			
			Aula aula = new Aula(dados);
			aula.setEstudoUsuario(estudoUsuario);
			aula.setTema(tema);
			
			aula = this.repository.save(aula);
			
			return new AulaDTO(aula);
		
		}catch(ObjectNotFoundFromParameterException ex) {
			throw new IllegalParameterException(ex.getMessage());
		}
		
	}

	public AulaDTO alterar(Long idAula, AulaDadosDTO dados) {
		
		Optional<Aula> optAula = this.repository.findById(idAula);
		
		if(optAula.isEmpty()) throw new ObjectNotFoundFromParameterException("Erro! Aula não encontrada!");
		
		Aula aula = optAula.get();
		aula.setData(dados.getData());
		aula.setHoraInicio(dados.getHoraInicio());
		aula.setHoraTermino(dados.getHoraTermino());
		aula.setTempoDuracao(dados.getTempoDuracao());
		
		aula = this.repository.save(aula);
		
		return new AulaDTO(aula);
		
	}

	public AulaDTO buscarPorId(Long idAula) {
		
		Optional<Aula> optAula = this.repository.findById(idAula);
		
		if(optAula.isEmpty()) throw new ObjectNotFoundFromParameterException("Erro! Aula não encontrada!");
		
		return new AulaDTO(optAula.get());
		
	}

	public void excluir(Long idAula) {
		
		Optional<Aula> optAula = this.repository.findById(idAula);
		
		if(optAula.isEmpty()) throw new ObjectNotFoundFromParameterException("Erro! Aula não encontrada!");
		
		this.repository.delete(optAula.get());
		
	}

	public Page<AulaDTO> listar(Long idEstudoUsuario, Pageable paginacao) {
		
		try {
			
			EstudoUsuario estudoUsuario = this.estudoUsuarioService.buscarPorId(idEstudoUsuario, 0);
			
			Page<Aula> pagina = this.repository.findByEstudoUsuario(estudoUsuario, paginacao);
			
			return pagina.map(AulaDTO::new);
			
		}catch(ObjectNotFoundFromParameterException ex) {
			throw new IllegalParameterException(ex.getMessage());
		}
		
	}
	
	
	
}
