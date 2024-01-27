package br.com.felipeduarte.gerenciadordeestudo.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DisciplinaDadosDTO {
	
	@NotBlank(message = "Por favor informe o nome da disciplina")
	private String nome;

}
