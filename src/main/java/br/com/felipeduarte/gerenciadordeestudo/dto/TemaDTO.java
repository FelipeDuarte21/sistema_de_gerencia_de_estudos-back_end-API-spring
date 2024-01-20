package br.com.felipeduarte.gerenciadordeestudo.dto;

import br.com.felipeduarte.gerenciadordeestudo.entity.Tema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TemaDTO {

	private Long id;
	private String titulo;
	private Integer cargaHoraria;
	
	public TemaDTO(Tema tema) {
		this.id = tema.getId();
		this.titulo = tema.getTitulo();
		this.cargaHoraria = tema.getCargaHoraria();
	}
	
}
