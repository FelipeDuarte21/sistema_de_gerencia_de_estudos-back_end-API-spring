package br.com.felipeduarte.gerenciadordeestudo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.felipeduarte.gerenciadordeestudo.entity.EstudoUsuario;
import br.com.felipeduarte.gerenciadordeestudo.entity.Usuario;

@Repository
public interface EstudoUsuarioRepository extends JpaRepository<EstudoUsuario, Long>{

	public Page<EstudoUsuario> findByUsuario(Usuario usuario, Pageable paginacao);
	
}
