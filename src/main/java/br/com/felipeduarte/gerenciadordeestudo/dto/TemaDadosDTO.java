package br.com.felipeduarte.gerenciadordeestudo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TemaDadosDTO {

	private Long id;
	private String titulo;
	private Integer cargaHoraria;
	
}
