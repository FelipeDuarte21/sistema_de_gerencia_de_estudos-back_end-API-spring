package br.com.felipeduarte.gerenciadordeestudo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.felipeduarte.gerenciadordeestudo.entity.Aula;

@Repository
public interface AulaRepository extends JpaRepository<Aula, Long> {

}
