package br.com.zupacademy.fpsaraiva.mercadolivre.dto;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class LoginRequest {

    @NotBlank
    @Email
    private String login;

    @NotBlank
    @Size(min = 6)
    private String senhaLimpa;

    public String getLogin() {
        return login;
    }

    public String getSenhaLimpa() {
        return senhaLimpa;
    }

    public UsernamePasswordAuthenticationToken converter() {
        return new UsernamePasswordAuthenticationToken(login, senhaLimpa);
    }

}
