package br.com.felipeduarte.gerenciadordeestudo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.felipeduarte.gerenciadordeestudo.entity.Disciplina;
import br.com.felipeduarte.gerenciadordeestudo.entity.Estudo;

@Repository
public interface DisciplinaRepository extends JpaRepository<Disciplina, Long> {
	
	public Page<Disciplina> findByEstudo(Estudo estudo, Pageable paginacao);

}
