package br.com.felipeduarte.gerenciadordeestudo.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.felipeduarte.gerenciadordeestudo.dto.UsuarioDTO;
import br.com.felipeduarte.gerenciadordeestudo.dto.UsuarioDadosDTO;
import br.com.felipeduarte.gerenciadordeestudo.entity.Usuario;
import br.com.felipeduarte.gerenciadordeestudo.entity.enums.TipoUsuario;
import br.com.felipeduarte.gerenciadordeestudo.repository.UsuarioRepository;
import br.com.felipeduarte.gerenciadordeestudo.service.exceptions.ObjectNotFoundFromParameterException;

@Service
public class UsuarioService {

	private UsuarioRepository repository;
	
	private BCryptPasswordEncoder bCrypt;
	
	public UsuarioService(UsuarioRepository repository, BCryptPasswordEncoder bCrypt) {
		this.repository = repository;
		this.bCrypt = bCrypt;
	}

	public UsuarioDTO cadastrar(UsuarioDadosDTO dados) {
		
		Usuario usuario = new Usuario(dados);
		usuario.setSenha(this.bCrypt.encode(usuario.getSenha()));
		
		usuario = this.repository.save(usuario);
		
		return new UsuarioDTO(usuario);
		
	}

	public UsuarioDTO alterar(Long idUsuario, UsuarioDadosDTO dados) {
		
		Optional<Usuario> optUsuario = this.repository.findById(idUsuario);
		
		if(optUsuario.isEmpty()) throw new ObjectNotFoundFromParameterException("Erro! Usuário Não Encontrado!");
		
		Usuario usuario = optUsuario.get();
		usuario.setNome(dados.getNome());
		usuario.setEmail(dados.getEmail());
		usuario.setSenha(dados.getSenha());
		usuario.setTipo(TipoUsuario.toEnum(dados.getTipo()));
		
		usuario = this.repository.save(usuario);
		
		return new UsuarioDTO(usuario);
		
	}

	public UsuarioDTO buscarPorId(Long idUsuario) {
		
		Optional<Usuario> optUsuario = this.repository.findById(idUsuario);
		
		if(optUsuario.isEmpty()) throw new ObjectNotFoundFromParameterException("Erro! Usuário Não Encontrado!");
		
		return new UsuarioDTO(optUsuario.get());
		
	}
	
	public Usuario buscarPorId(Long idUsuario, int i) {
		
		Optional<Usuario> optUsuario = this.repository.findById(idUsuario);
		
		if(optUsuario.isEmpty()) throw new ObjectNotFoundFromParameterException("Erro! Usuário Não Encontrado!");
		
		return optUsuario.get();
	
	}
	
	public UsuarioDTO buscarPorEmail(String email) {
		
		Optional<Usuario> optUsuario = this.repository.findByEmail(email);
		
		if(optUsuario.isEmpty()) throw new ObjectNotFoundFromParameterException("Erro! Usuário Não Encontrado!");
		
		return new UsuarioDTO(optUsuario.get());
		
	}
	

	public void excluir(Long idUsuario) {
		
		Optional<Usuario> optUsuario = this.repository.findById(idUsuario);
		
		if(optUsuario.isEmpty()) throw new ObjectNotFoundFromParameterException("Erro! Usuário Não Encontrado!");
		
		this.repository.delete(optUsuario.get());
	
	}

	public Page<UsuarioDTO> listar(Pageable paginacao) {
		
		Page<Usuario> paginaUsuario = this.repository.findAll(paginacao);
		
		return paginaUsuario.map(UsuarioDTO::new);
		
	}

}
