package br.com.felipeduarte.gerenciadordeestudo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TemaMetricaDTO {
	
	private Integer cargaTotal;
	private Integer totalRealizado;
	private Integer totalRestante;
	private Boolean concluido;
	

}
