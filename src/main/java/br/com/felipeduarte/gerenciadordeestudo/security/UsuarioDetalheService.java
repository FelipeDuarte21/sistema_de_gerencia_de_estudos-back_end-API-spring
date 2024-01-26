package br.com.felipeduarte.gerenciadordeestudo.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.felipeduarte.gerenciadordeestudo.entity.Usuario;
import br.com.felipeduarte.gerenciadordeestudo.service.UsuarioService;
import br.com.felipeduarte.gerenciadordeestudo.service.exceptions.ObjectNotFoundFromParameterException;

import br.com.felipeduarte.gerenciadordeestudo.dto.UsuarioDTO;

@Service
public class UsuarioDetalheService implements UserDetailsService {
	
	@Autowired
	private UsuarioService usuarioService;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		try {
		
			UsuarioDTO usuario = this.usuarioService.buscarPorEmail(email);
			
			return new UsuarioDetalhe(usuario.getId(),usuario.getEmail(),usuario.getSenha(), usuario.getTipo());
		
		}catch(ObjectNotFoundFromParameterException ex) {
			
			throw new UsernameNotFoundException(email);
			
		}
		
	}
	
	public UserDetails loadUserById(Long id) throws UsernameNotFoundException {
		
		try {
		
			UsuarioDTO usuario = this.usuarioService.buscarPorId(id);
			
			return new UsuarioDetalhe(usuario.getId(),usuario.getEmail(),usuario.getSenha(), usuario.getTipo());
		
		}catch(ObjectNotFoundFromParameterException ex) {
			
			throw new UsernameNotFoundException(id.toString());
			
		}
		
	}
	
	public Optional<UsuarioDetalhe> getUsuarioAutenticado() throws Exception {
		
		try {
			
			UsuarioDetalhe usuarioDetalhe = (UsuarioDetalhe) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			
			Usuario usuario = new Usuario();
			usuario.setId(usuarioDetalhe.getId());
			usuario.setEmail(usuarioDetalhe.getUsername());
			usuario.setSenha(usuarioDetalhe.getPassword());
			//usuario.setTipo( usuarioDetalhe.getAuthorities().hey );
			
			return Optional.of(new UsuarioDetalhe(usuario));
			
		}catch(Exception e) {
			throw new Exception("Erro! O usuario nao está autenticado!");
		}
	}

}
