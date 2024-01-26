package br.com.felipeduarte.gerenciadordeestudo.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.felipeduarte.gerenciadordeestudo.entity.Usuario;

public class UsuarioDetalhe implements UserDetails {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String email;
	private String senha;
	
	private Collection<? extends GrantedAuthority> autorizacoes;
	
	public UsuarioDetalhe(Long id, String email,String senha, Integer tipo) {
		this.id = id;
		this.email = email;
		this.senha = senha;
		
		Collection<SimpleGrantedAuthority> auth = new ArrayList<>();
		auth.add(new SimpleGrantedAuthority(String.valueOf(tipo)));
		
		this.autorizacoes = auth;
	}
	
	public UsuarioDetalhe(Usuario usuario) {
		this.id = usuario.getId();
		this.email = usuario.getEmail();
		this.senha = usuario.getSenha();
		
		Collection<SimpleGrantedAuthority> auth = new ArrayList<>();
		auth.add(new SimpleGrantedAuthority(String.valueOf(usuario.getTipo())));
		
		this.autorizacoes = auth;
		
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.autorizacoes;
	}
	
	public Long getId() {
		return this.id;
	}

	@Override
	public String getPassword() {
		return this.senha;
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
	
	public boolean hasRole(String role) {
		return this.autorizacoes.contains(new SimpleGrantedAuthority(role));
	}

}
