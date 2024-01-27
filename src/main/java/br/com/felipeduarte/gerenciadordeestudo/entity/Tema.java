package br.com.felipeduarte.gerenciadordeestudo.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.com.felipeduarte.gerenciadordeestudo.dto.TemaDadosDTO;
import jakarta.persistence.Entity;
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
@Table(name = "tema")
public class Tema implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String titulo;
	
	private Integer cargaHoraria;

	@ManyToOne
	@JoinColumn(name = "id_disciplina")
	private Disciplina disciplina;
	
	@OneToMany(mappedBy = "tema")
	private List<Aula> aulas = new ArrayList<>();
	
	public Tema(TemaDadosDTO dados) {
		this.titulo = dados.getTitulo();
		this.cargaHoraria = dados.getCargaHoraria();
	}
	
}
