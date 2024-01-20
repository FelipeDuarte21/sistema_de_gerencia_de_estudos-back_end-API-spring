package br.com.felipeduarte.gerenciadordeestudo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EstudoDadosDTO {

	private Long id;
	private String nome;
	private String descricao;
	
}
