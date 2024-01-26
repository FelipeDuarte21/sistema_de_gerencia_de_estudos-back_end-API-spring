package br.com.felipeduarte.gerenciadordeestudo.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.felipeduarte.gerenciadordeestudo.dto.TemaDTO;
import br.com.felipeduarte.gerenciadordeestudo.dto.TemaDadosDTO;
import br.com.felipeduarte.gerenciadordeestudo.entity.Disciplina;
import br.com.felipeduarte.gerenciadordeestudo.entity.Tema;
import br.com.felipeduarte.gerenciadordeestudo.repository.TemaRepository;
import br.com.felipeduarte.gerenciadordeestudo.service.exceptions.ObjectNotFoundFromParameterException;

@Service
public class TemaService {

	private TemaRepository repository;
	
	private DisciplinaService disciplinaService;
	
	public TemaService(TemaRepository repository, DisciplinaService disciplinaService ) {
		this.repository = repository;
		this.disciplinaService = disciplinaService;
	}

	public TemaDTO cadastrar(Long idDisciplina, TemaDadosDTO dados) {
		
		Disciplina disciplina = this.disciplinaService.buscarPorId(idDisciplina,0);
		
		Tema tema = new Tema(dados);
		tema.setDisciplina(disciplina);
		
		tema = this.repository.save(tema);
		
		return new TemaDTO(tema);
		
	}

	public TemaDTO alterar(Long idTema, TemaDadosDTO dados) {
		
		Optional<Tema> optTema = this.repository.findById(idTema);
		
		if(optTema.isEmpty()) throw new ObjectNotFoundFromParameterException("Erro! Tema não foi encontrado!");
		
		Tema tema = optTema.get();
		tema.setTitulo(dados.getTitulo());
		tema.setCargaHoraria(dados.getCargaHoraria());
		
		tema = this.repository.save(tema);
		
		return new TemaDTO(tema);
		
	}

	public TemaDTO buscarPorId(Long idTema) {
		
		Optional<Tema> optTema = this.repository.findById(idTema);
		
		if(optTema.isEmpty()) throw new ObjectNotFoundFromParameterException("Erro! Tema não foi encontrado!");
		
		return new TemaDTO(optTema.get());
		
	}
	
	public Tema buscarPorId(Long idTema, int a) {
		
		Optional<Tema> optTema = this.repository.findById(idTema);
		
		if(optTema.isEmpty()) throw new ObjectNotFoundFromParameterException("Erro! Tema não foi encontrado!");
		
		return optTema.get();
		
	}

	public void excluir(Long idTema) {
		
		Optional<Tema> optTema = this.repository.findById(idTema);
		
		if(optTema.isEmpty()) throw new ObjectNotFoundFromParameterException("Erro! Tema não foi encontrado!");
		
		this.repository.delete(optTema.get());
		
	}

	public Page<TemaDTO> listar(Long idDisciplina, Pageable paginacao) {
		
		Disciplina disciplina = this.disciplinaService.buscarPorId(idDisciplina, 0);
		
		Page<Tema> paginaTema = this.repository.findByDisciplina(disciplina, paginacao);
		
		return paginaTema.map(TemaDTO::new);
		
	}
	
}
