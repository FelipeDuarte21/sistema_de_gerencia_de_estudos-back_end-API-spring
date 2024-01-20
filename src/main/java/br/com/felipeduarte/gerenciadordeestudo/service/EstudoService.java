package br.com.felipeduarte.gerenciadordeestudo.service;

import org.springframework.stereotype.Service;

import br.com.felipeduarte.gerenciadordeestudo.repository.EstudoRepository;

@Service
public class EstudoService {

	private EstudoRepository repository;
	
	public EstudoService(EstudoRepository repository) {
		this.repository = repository;
	}
	
}
