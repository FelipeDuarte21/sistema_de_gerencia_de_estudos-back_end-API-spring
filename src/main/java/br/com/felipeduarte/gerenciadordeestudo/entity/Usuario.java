package br.com.felipeduarte.gerenciadordeestudo.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.com.felipeduarte.gerenciadordeestudo.dto.UsuarioDadosDTO;
import br.com.felipeduarte.gerenciadordeestudo.entity.enums.TipoUsuario;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "usuario")
public class Usuario implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	 
	private String email;
	
	private String senha;
	
	@Enumerated(EnumType.ORDINAL)
	private TipoUsuario tipo;

	@OneToMany(mappedBy = "usuario", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
	private List<EstudoUsuario> estudosUsuario = new ArrayList<>();
	
	public Usuario(UsuarioDadosDTO dados) {
		this.nome = dados.getNome();
		this.email = dados.getEmail();
		this.senha = dados.getSenha();
		this.tipo = TipoUsuario.toEnum(dados.getTipo());
	}
	
}
