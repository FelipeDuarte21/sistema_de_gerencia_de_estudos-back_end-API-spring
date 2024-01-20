package br.com.felipeduarte.gerenciadordeestudo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.felipeduarte.gerenciadordeestudo.entity.EstudoUsuario;

@Repository
public interface EstudoUsuarioRepository extends JpaRepository<EstudoUsuario, Long>{

}
