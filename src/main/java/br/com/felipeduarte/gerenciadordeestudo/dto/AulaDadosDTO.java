package br.com.felipeduarte.gerenciadordeestudo.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AulaDadosDTO {
	
	@NotBlank(message = "Por favor informe a data !!")
	private LocalDate data;
	
	@NotBlank(message = "Por favor informe a hora de Inicio !!")
	private LocalTime horaInicio;
	
	@NotBlank(message = "Por favor informe a hora de Termino !!")
	private LocalTime horaTermino;
	
	@NotBlank(message = "Por favor informe o tempo de duração !!")
	private Integer tempoDuracao;
	
	@NotBlank(message = "Por favor informe o estudo do usuario !!")
	private Long idEstudoUsuario;
	
	@NotBlank(message = "Por favor informe o tema !!")
	private Long idTema;

}
