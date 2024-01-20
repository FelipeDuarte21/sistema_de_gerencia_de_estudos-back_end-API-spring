package br.com.felipeduarte.gerenciadordeestudo.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.felipeduarte.gerenciadordeestudo.entity.EstudoUsuario;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EstudoUsuarioDTO {
	
	private Long id;
	private LocalDate dataInscricao;
	private BigDecimal valorPago;
	private EstudoDTO estudo;
	private List<AulaDTO> aulas = new ArrayList<>();

	public EstudoUsuarioDTO(EstudoUsuario estudoUsuario) {
		this.id = estudoUsuario.getId();
		this.dataInscricao = estudoUsuario.getDataInscricao();
		this.valorPago = estudoUsuario.getValorPago();
		this.estudo = new EstudoDTO(estudoUsuario.getEstudo());
		this.aulas = estudoUsuario.getAulas().stream().map(AulaDTO::new).collect(Collectors.toList());
	}
	
}
