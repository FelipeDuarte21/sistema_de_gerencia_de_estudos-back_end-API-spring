package br.com.felipeduarte.gerenciadordeestudo.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EstudoDadosDTO {

	@NotBlank(message = "Por favor infome o nome do Estudo!!")
	private String nome;
	
	private String descricao;
	
}
