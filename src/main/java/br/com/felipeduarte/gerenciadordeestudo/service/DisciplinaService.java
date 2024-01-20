package br.com.felipeduarte.gerenciadordeestudo.service;

import org.springframework.stereotype.Service;

import br.com.felipeduarte.gerenciadordeestudo.repository.DisciplinaRepository;

@Service
public class DisciplinaService {

	private DisciplinaRepository repository;
	
	public DisciplinaService(DisciplinaRepository repository) {
		this.repository = repository;
	}
}
