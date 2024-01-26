package br.com.felipeduarte.gerenciadordeestudo.dto;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public class LoginDTO {

    @NotNull(message = "Informe o campo email")
    @Email(message = "o valor informado está fora do padrão para email")
    private String email;

    @NotNull(message = "Informe o campo senha")
    private String senha;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public UsernamePasswordAuthenticationToken converter() {
        return new UsernamePasswordAuthenticationToken(email, senha);
    }

}
