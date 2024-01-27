package br.com.felipeduarte.gerenciadordeestudo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UsuarioDadosDTO {

	@NotBlank(message = "Por favor informe o nome do usuario")
	private String nome;
	
	@NotBlank(message = "Por favor informe o email")
	@Email(message = "Email inválido!!")
	private String email;
	
	@NotBlank(message = "Por favor informe a senha!!")
	private String senha;
	
	@NotBlank(message = "Por favor informe o tipo do usuario !!")
	private Integer tipo;
	
}
