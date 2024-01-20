package br.com.felipeduarte.gerenciadordeestudo.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import br.com.felipeduarte.gerenciadordeestudo.entity.Aula;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AulaDTO {

	private Long id;
	private LocalDate data;
	private LocalTime horaInicio;
	private LocalTime horaTermino;
	private Integer tempoDuracao;
	private TemaDTO tema;
	
	public AulaDTO(Aula aula) {
		this.id = aula.getId();
		this.data = aula.getData();
		this.horaInicio = aula.getHoraInicio();
		this.horaTermino = aula.getHoraTermino();
		this.tempoDuracao = aula.getTempoDuracao();
		this.tema = new TemaDTO(aula.getTema());
	}
	
}
