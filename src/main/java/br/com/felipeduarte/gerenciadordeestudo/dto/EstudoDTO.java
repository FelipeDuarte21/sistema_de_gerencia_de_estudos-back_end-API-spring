package br.com.felipeduarte.gerenciadordeestudo.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.felipeduarte.gerenciadordeestudo.entity.Estudo;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EstudoDTO {
	
	private Long id;
	private String nome;
	private String descricao;
	private List<DisciplinaDTO> disciplinas = new ArrayList<>();
	
	public EstudoDTO(Estudo estudo) {
		this.id = estudo.getId();
		this.nome = estudo.getNome();
		this.descricao = estudo.getDescricao();
		this.disciplinas = estudo.getDisciplinas().stream().map(DisciplinaDTO::new).collect(Collectors.toList());
	}

	
	
}
