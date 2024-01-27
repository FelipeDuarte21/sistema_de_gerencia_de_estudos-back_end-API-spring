package br.com.felipeduarte.gerenciadordeestudo.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.com.felipeduarte.gerenciadordeestudo.dto.DisciplinaDadosDTO;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "disciplina")
public class Disciplina implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	
	@ManyToOne
	@JoinColumn(name = "id_estudo")
	private Estudo estudo;
	
	@OneToMany(mappedBy = "disciplina", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	private List<Tema> temas = new ArrayList<>();
	
	public Disciplina(DisciplinaDadosDTO dados) {
		this.nome = dados.getNome();
	}

}
