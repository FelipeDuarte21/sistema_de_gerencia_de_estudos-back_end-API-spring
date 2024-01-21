package br.com.felipeduarte.gerenciadordeestudo.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.felipeduarte.gerenciadordeestudo.dto.EstudoUsuarioDTO;
import br.com.felipeduarte.gerenciadordeestudo.dto.EstudoUsuarioDadosDTO;
import br.com.felipeduarte.gerenciadordeestudo.entity.Estudo;
import br.com.felipeduarte.gerenciadordeestudo.entity.EstudoUsuario;
import br.com.felipeduarte.gerenciadordeestudo.entity.Usuario;
import br.com.felipeduarte.gerenciadordeestudo.repository.EstudoUsuarioRepository;
import br.com.felipeduarte.gerenciadordeestudo.service.exceptions.IllegalParameterException;
import br.com.felipeduarte.gerenciadordeestudo.service.exceptions.ObjectNotFoundFromParameterException;

@Service
public class EstudoUsuarioService {

	private EstudoUsuarioRepository repository;
	
	private EstudoService estudoService;
	private UsuarioService usuarioService;
	
	public EstudoUsuarioService(EstudoUsuarioRepository repository, EstudoService estudoService,
			UsuarioService usuarioService) {
		this.repository = repository;
		this.estudoService = estudoService;
		this.usuarioService = usuarioService;
	}

	public EstudoUsuarioDTO cadastrar(EstudoUsuarioDadosDTO dados) {
		
		try {
		
			Estudo estudo = this.estudoService.buscarPorId(dados.getIdEstudo(), 0);
			Usuario usuario = this.usuarioService.buscarPorId(dados.getIdUsuario(),0);
			
			EstudoUsuario estudoUsuario = new EstudoUsuario(dados);
			estudoUsuario.setEstudo(estudo);
			estudoUsuario.setUsuario(usuario);
			
			estudoUsuario = this.repository.save(estudoUsuario);
			
			return new EstudoUsuarioDTO(estudoUsuario);
		
		}catch(ObjectNotFoundFromParameterException ex) {
			throw new IllegalParameterException(ex.getMessage());
		}
		
	}
	
	public EstudoUsuarioDTO alterar(Long idEstudoUsuario, EstudoUsuarioDadosDTO dados) {
		
		Optional<EstudoUsuario> optEstudoUsuario = this.repository.findById(idEstudoUsuario);
		
		if(optEstudoUsuario.isEmpty()) throw new ObjectNotFoundFromParameterException("Erro! EstudoUsuario não encontrado!");
		
		EstudoUsuario estudoUsuario = optEstudoUsuario.get();
		estudoUsuario.setDataInscricao(dados.getDataInscricao());
		estudoUsuario.setValorPago(dados.getValorPago());
		
		estudoUsuario = this.repository.save(estudoUsuario);
		
		return new EstudoUsuarioDTO(estudoUsuario);
		
	}
	
	public EstudoUsuarioDTO buscarPorId(Long idEstudoUsuario) {
		
		Optional<EstudoUsuario> optEstudoUsuario = this.repository.findById(idEstudoUsuario);
		
		if(optEstudoUsuario.isEmpty()) throw new ObjectNotFoundFromParameterException("Erro! EstudoUsuario não econtrado!");
		
		return new EstudoUsuarioDTO(optEstudoUsuario.get());
		
	}
	

	public void excluir(Long idEstudoUsuario) {
		
		Optional<EstudoUsuario> optEstudoUsuario = this.repository.findById(idEstudoUsuario);
		
		if(optEstudoUsuario.isEmpty()) throw new ObjectNotFoundFromParameterException("Erro! EstudoUsuario não econtrado!");
		
		this.repository.delete(optEstudoUsuario.get());
		
	}

	public Page<EstudoUsuarioDTO> listar(Long idUsuario, Pageable paginacao) {
		
		Usuario usuario = this.usuarioService.buscarPorId(idUsuario, 0);
	
		Page<EstudoUsuario> pagina = this.repository.findByUsuario(usuario, paginacao);
		
		return pagina.map(EstudoUsuarioDTO::new);
		
	}

	

	
}
