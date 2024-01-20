package br.com.felipeduarte.gerenciadordeestudo.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.felipeduarte.gerenciadordeestudo.dto.DisciplinaDTO;
import br.com.felipeduarte.gerenciadordeestudo.dto.DisciplinaDadosDTO;
import br.com.felipeduarte.gerenciadordeestudo.entity.Disciplina;
import br.com.felipeduarte.gerenciadordeestudo.entity.Estudo;
import br.com.felipeduarte.gerenciadordeestudo.repository.DisciplinaRepository;
import br.com.felipeduarte.gerenciadordeestudo.service.exceptions.ObjectNotFoundFromParameterException;

@Service
public class DisciplinaService {

	private DisciplinaRepository repository;
	
	private EstudoService estudoService;
	
	public DisciplinaService(DisciplinaRepository repository, EstudoService estudoService) {
		this.repository = repository;
		this.estudoService = estudoService;
	}

	public DisciplinaDTO cadastrar(Long idEstudo, DisciplinaDadosDTO dados) {
		
		Estudo estudo = this.estudoService.buscarPorId(idEstudo,0);
		
		Disciplina disciplina = new Disciplina(dados);
		disciplina.setEstudo(estudo);
		
		disciplina = this.repository.save(disciplina);
		
		return new DisciplinaDTO(disciplina);
		
	}

	public DisciplinaDTO alterar(Long idDisciplina, DisciplinaDadosDTO dados) {
		
		Optional<Disciplina> optDisciplina = this.repository.findById(idDisciplina);
		
		if(optDisciplina.isEmpty()) throw new ObjectNotFoundFromParameterException("Erro! Disciplina não encontrada!");
		
		Disciplina disciplina = optDisciplina.get();
		disciplina.setNome(dados.getNome());
		
		disciplina = this.repository.save(disciplina);
		
		return new DisciplinaDTO(disciplina);
		
	}

	public DisciplinaDTO buscarPorId(Long idDisciplina) {
		
		Optional<Disciplina> optDisciplina = this.repository.findById(idDisciplina);
		
		if(optDisciplina.isEmpty()) throw new ObjectNotFoundFromParameterException("Erro! Disciplina não encontrada!");
		
		return new DisciplinaDTO(optDisciplina.get());
		
	}
	
	public Disciplina buscarPorId(Long idDisciplina, int i) {
		
		Optional<Disciplina> optDisciplina = this.repository.findById(idDisciplina);
		
		if(optDisciplina.isEmpty()) throw new ObjectNotFoundFromParameterException("Erro! Disciplina não encontrada!");
		
		return optDisciplina.get();
		
	}

	public void excluir(Long idDisciplina) {
		
		Optional<Disciplina> optDisciplina = this.repository.findById(idDisciplina);
		
		if(optDisciplina.isEmpty()) throw new ObjectNotFoundFromParameterException("Erro! Disciplina não encontrada!");
		
		this.repository.delete(optDisciplina.get());
		
	}

	public Page<DisciplinaDTO> listar(Long idEstudo, Pageable paginacao) {
		
		Estudo estudo = this.estudoService.buscarPorId(idEstudo, 0);
		
		Page<Disciplina> paginaDisciplina = this.repository.findByEstudo(estudo, paginacao);
		
		return paginaDisciplina.map(DisciplinaDTO::new);
		
	}

	
	
}
