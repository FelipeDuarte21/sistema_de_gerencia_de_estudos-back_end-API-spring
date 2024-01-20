package br.com.felipeduarte.gerenciadordeestudo.service;

import org.springframework.stereotype.Service;

import br.com.felipeduarte.gerenciadordeestudo.repository.UsuarioRepository;

@Service
public class UsuarioService {

	private UsuarioRepository repository;
	
	public UsuarioService(UsuarioRepository repository) {
		this.repository = repository;
	}
	
}
