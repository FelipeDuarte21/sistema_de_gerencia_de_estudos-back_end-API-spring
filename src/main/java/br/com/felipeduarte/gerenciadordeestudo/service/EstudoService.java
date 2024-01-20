package br.com.felipeduarte.gerenciadordeestudo.service;

import org.springframework.stereotype.Service;

import br.com.felipeduarte.gerenciadordeestudo.dto.EstudoDTO;
import br.com.felipeduarte.gerenciadordeestudo.dto.EstudoDadosDTO;
import br.com.felipeduarte.gerenciadordeestudo.entity.Estudo;
import br.com.felipeduarte.gerenciadordeestudo.repository.EstudoRepository;

@Service
public class EstudoService {

	private EstudoRepository repository;
	
	public EstudoService(EstudoRepository repository) {
		this.repository = repository;
	}

	/*
	public EstudoDTO cadastrar(EstudoDadosDTO dados) {
		
		Estudo estudo = new Estudo(dados);
		
		estudo = this.repository.save(estudo);
		
		return new EstudoDTO(estudo);
	
	}*/
	
}
