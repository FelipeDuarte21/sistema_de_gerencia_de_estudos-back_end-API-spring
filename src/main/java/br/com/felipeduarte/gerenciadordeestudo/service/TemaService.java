package br.com.felipeduarte.gerenciadordeestudo.service;

import org.springframework.stereotype.Service;

import br.com.felipeduarte.gerenciadordeestudo.repository.TemaRepository;

@Service
public class TemaService {

	private TemaRepository repository;
	
	public TemaService(TemaRepository repository) {
		this.repository = repository;
	}
	
}
