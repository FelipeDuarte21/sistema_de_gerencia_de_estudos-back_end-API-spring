package br.com.felipeduarte.gerenciadordeestudo.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EstudoUsuarioDadosDTO {
	
	@NotBlank(message = "Por favor informa a data de inscricao")
	private LocalDate dataInscricao;
	
	@NotBlank(message = "Por favor informe o valor pago")
	private BigDecimal valorPago;
	
	@NotBlank(message = "Por favor informe o estudo do usuario!!")
	private Long idEstudo;
	
	@NotBlank(message = "Por favor informe o usuario!!")
	private Long idUsuario;

}
