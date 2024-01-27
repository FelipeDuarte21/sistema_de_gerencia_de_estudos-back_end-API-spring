package br.com.felipeduarte.gerenciadordeestudo.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TemaDadosDTO {

	@NotBlank(message = "Por favor informe o título do Tema !!")
	private String titulo;
	
	@NotBlank(message = "Por favor informe a carga horaria total do Tema")
	private Integer cargaHoraria;
	
}
