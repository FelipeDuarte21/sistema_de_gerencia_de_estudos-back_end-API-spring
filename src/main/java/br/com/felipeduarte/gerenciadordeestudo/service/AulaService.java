package br.com.felipeduarte.gerenciadordeestudo.service;

import org.springframework.stereotype.Service;

import br.com.felipeduarte.gerenciadordeestudo.repository.AulaRepository;

@Service
public class AulaService {

	private AulaRepository repository;
	
	public AulaService(AulaRepository repository) {
		this.repository = repository;
	}
	
}
