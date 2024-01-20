package br.com.felipeduarte.gerenciadordeestudo.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.felipeduarte.gerenciadordeestudo.dto.EstudoDTO;
import br.com.felipeduarte.gerenciadordeestudo.dto.EstudoDadosDTO;
import br.com.felipeduarte.gerenciadordeestudo.entity.Estudo;
import br.com.felipeduarte.gerenciadordeestudo.repository.EstudoRepository;
import br.com.felipeduarte.gerenciadordeestudo.service.exceptions.ObjectNotFoundFromParameterException;

@Service
public class EstudoService {

	private EstudoRepository repository;
	
	public EstudoService(EstudoRepository repository) {
		this.repository = repository;
	}

	public EstudoDTO cadastrar(EstudoDadosDTO dados) {
		
		Estudo estudo = new Estudo(dados);
		
		estudo = this.repository.save(estudo);
		
		return new EstudoDTO(estudo);
	
	}

	public EstudoDTO alterar(Long idEstudo, EstudoDadosDTO dados) {
		
		Optional<Estudo> optEstudo = this.repository.findById(idEstudo);
		
		if(optEstudo.isEmpty()) throw new ObjectNotFoundFromParameterException("Erro! Estudo não encontrado!");
		
		Estudo estudo = optEstudo.get();
		estudo.setNome(dados.getNome());
		estudo.setDescricao(dados.getDescricao());
		
		estudo = this.repository.save(estudo);
		
		return new EstudoDTO(estudo);
		
	}

	public EstudoDTO buscarPorId(Long idEstudo) {
		
		Optional<Estudo> optEstudo = this.repository.findById(idEstudo);
		
		if(optEstudo.isEmpty()) throw new ObjectNotFoundFromParameterException("Erro! Estudo não encontrado!");
		
		return new EstudoDTO(optEstudo.get());
		
	}

	public void excluir(Long idEstudo) {
		
		Optional<Estudo> optEstudo = this.repository.findById(idEstudo);
		
		if(optEstudo.isEmpty()) throw new ObjectNotFoundFromParameterException("Erro! Estudo não encontrado!");
		
		this.repository.delete(optEstudo.get());
		
	}

	public Page<EstudoDTO> listar(Pageable paginacao) {
		
		Page<Estudo> paginaEstudos =  this.repository.findAll(paginacao);
		
		return paginaEstudos.map(EstudoDTO::new);
		
	}
	
}
