package br.com.felipeduarte.gerenciadordeestudo.service;

import org.springframework.stereotype.Service;

import br.com.felipeduarte.gerenciadordeestudo.repository.EstudoUsuarioRepository;

@Service
public class EstudoUsuarioService {

	private EstudoUsuarioRepository repository;
	
	public EstudoUsuarioService(EstudoUsuarioRepository repository) {
		this.repository = repository;
	}
	
}
