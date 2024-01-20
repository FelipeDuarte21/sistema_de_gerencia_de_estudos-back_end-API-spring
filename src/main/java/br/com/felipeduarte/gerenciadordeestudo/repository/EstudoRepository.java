package br.com.felipeduarte.gerenciadordeestudo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.felipeduarte.gerenciadordeestudo.entity.Estudo;

@Repository
public interface EstudoRepository extends JpaRepository<Estudo, Long> {

}
