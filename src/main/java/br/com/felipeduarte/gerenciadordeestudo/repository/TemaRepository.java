package br.com.felipeduarte.gerenciadordeestudo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.felipeduarte.gerenciadordeestudo.entity.Disciplina;
import br.com.felipeduarte.gerenciadordeestudo.entity.Tema;

@Repository
public interface TemaRepository extends JpaRepository<Tema, Long> {
	
	public Page<Tema> findByDisciplina(Disciplina disciplina, Pageable paginacao);

}
