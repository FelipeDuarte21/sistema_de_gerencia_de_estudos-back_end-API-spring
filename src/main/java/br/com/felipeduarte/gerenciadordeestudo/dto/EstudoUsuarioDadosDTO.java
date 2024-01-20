package br.com.felipeduarte.gerenciadordeestudo.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EstudoUsuarioDadosDTO {
	
	private Long id;
	private LocalDate dataInscricao;
	private BigDecimal valorPago;
	private Long idEstudo;
	private Long idUsuario;

}
