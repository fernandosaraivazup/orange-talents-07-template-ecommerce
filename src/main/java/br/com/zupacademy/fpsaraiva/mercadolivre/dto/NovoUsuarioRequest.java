package br.com.zupacademy.fpsaraiva.mercadolivre.dto;

import br.com.zupacademy.fpsaraiva.mercadolivre.model.Usuario;
import br.com.zupacademy.fpsaraiva.mercadolivre.shared.senha.SenhaLimpa;
import br.com.zupacademy.fpsaraiva.mercadolivre.shared.validacoes.ValorUnico;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class NovoUsuarioRequest {

    @NotBlank
    @Email
    @ValorUnico(domainClass = Usuario.class, fieldName = "login", message = "ERRO: Login já cadastrado no sistema.")
    private String login;
    @NotBlank
    @Length(min = 6)
    private String senha;

    public NovoUsuarioRequest(@NotBlank @Email String login,@NotBlank @Length(min = 6) String senha) {
        this.login = login;
        this.senha = senha;
    }

    public Usuario toModel() {
        return new Usuario(login, new SenhaLimpa(senha));
    }

}
