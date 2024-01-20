package br.com.felipeduarte.gerenciadordeestudo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UsuarioDadosDTO {

	private Long id;
	private String nome;
	private String email;
	private String senha;
	private Integer tipo;
	
}
