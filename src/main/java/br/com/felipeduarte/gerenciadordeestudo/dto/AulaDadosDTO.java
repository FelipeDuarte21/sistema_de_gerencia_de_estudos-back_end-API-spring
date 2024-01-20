package br.com.felipeduarte.gerenciadordeestudo.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AulaDadosDTO {
	
	private Long id;
	private LocalDate data;
	private LocalTime horaInicio;
	private LocalTime horaTermino;
	private Integer tempoDuracao;
	private Long idEstudoUsuario;
	private Long idTema;

}
