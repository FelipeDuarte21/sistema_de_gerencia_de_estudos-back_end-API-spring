package br.com.felipeduarte.gerenciadordeestudo.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.com.felipeduarte.gerenciadordeestudo.dto.EstudoDadosDTO;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "estudo")
public class Estudo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	
	@Column(columnDefinition = "text")
	private String descricao;
	
	@OneToMany(mappedBy = "estudo", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	private List<Disciplina> disciplinas = new ArrayList<>();
	
	@OneToMany(mappedBy = "estudo", cascade = CascadeType.REMOVE)
	private List<EstudoUsuario> estudosUsuario = new ArrayList<>();
	
	public Estudo(EstudoDadosDTO dados) {
		this.id = dados.getId();
		this.nome = dados.getNome();
		this.descricao = dados.getDescricao();
	}
	
}
