package br.com.felipeduarte.gerenciadordeestudo.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.felipeduarte.gerenciadordeestudo.dto.EstudoUsuarioDadosDTO;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "estudo_usuario")
public class EstudoUsuario implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private LocalDate dataInscricao;
	
	private BigDecimal valorPago;
	
	@ManyToOne
	@JoinColumn(name = "id_estudo")
	private Estudo estudo;
	
	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;
	
	@OneToMany(mappedBy = "estudoUsuario", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	private List<Aula> aulas = new ArrayList<>();
	
	public EstudoUsuario(EstudoUsuarioDadosDTO dados) {
		this.id = dados.getId();
		this.dataInscricao = LocalDate.now();
		this.valorPago = dados.getValorPago();
	}

}
