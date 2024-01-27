package br.com.felipeduarte.gerenciadordeestudo.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

import br.com.felipeduarte.gerenciadordeestudo.dto.AulaDadosDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "aula")
public class Aula implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private LocalDate data;
	
	private LocalTime horaInicio;
	
	private LocalTime horaTermino;
	
	private Integer tempoDuracao;

	@ManyToOne
	@JoinColumn(name = "id_estudo_usuario")
	private EstudoUsuario estudoUsuario;
	
	@ManyToOne
	@JoinColumn(name = "id_tema")
	private Tema tema;
	
	public Aula(AulaDadosDTO dados) {
		this.data = dados.getData();
		this.horaInicio = dados.getHoraInicio();
		this.horaTermino = dados.getHoraTermino();
		this.tempoDuracao = dados.getTempoDuracao();
	}
	
}
