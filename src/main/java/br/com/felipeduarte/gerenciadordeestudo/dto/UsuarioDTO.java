package br.com.felipeduarte.gerenciadordeestudo.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.felipeduarte.gerenciadordeestudo.entity.Usuario;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UsuarioDTO {

	private Long id;
	private String nome;
	private String email;
	private String senha;
	private Integer tipo;
	private List<EstudoUsuarioDTO> estudosUsuario = new ArrayList<>();
	
	public UsuarioDTO(Usuario usuario) {
		this.id = usuario.getId();
		this.nome = usuario.getNome();
		this.email = usuario.getEmail();
		this.senha = usuario.getSenha();
		this.tipo = usuario.getTipo().getCodigo();
		this.estudosUsuario = usuario.getEstudosUsuario().stream().map(EstudoUsuarioDTO::new).collect(Collectors.toList());
	}
	
}
