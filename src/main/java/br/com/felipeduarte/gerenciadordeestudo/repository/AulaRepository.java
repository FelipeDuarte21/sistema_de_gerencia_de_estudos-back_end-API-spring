package br.com.felipeduarte.gerenciadordeestudo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.felipeduarte.gerenciadordeestudo.entity.Aula;
import br.com.felipeduarte.gerenciadordeestudo.entity.EstudoUsuario;

@Repository
public interface AulaRepository extends JpaRepository<Aula, Long> {
	
	public Page<Aula> findByEstudoUsuario(EstudoUsuario estudoUsuario, Pageable paginacao);

}
