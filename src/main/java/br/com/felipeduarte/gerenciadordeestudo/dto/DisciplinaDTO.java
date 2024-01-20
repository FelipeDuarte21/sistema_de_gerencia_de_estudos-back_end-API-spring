package br.com.felipeduarte.gerenciadordeestudo.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.felipeduarte.gerenciadordeestudo.entity.Disciplina;
import br.com.felipeduarte.gerenciadordeestudo.entity.Estudo;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DisciplinaDTO {

	private Long id;
	private String nome;
	private Estudo estudo;
	private List<TemaDTO> temas = new ArrayList<>();
	
	public DisciplinaDTO(Disciplina disciplina) {
		this.id = disciplina.getId();
		this.nome = disciplina.getNome();
		this.temas = disciplina.getTemas().stream().map(TemaDTO::new).collect(Collectors.toList());
	}
	
}
