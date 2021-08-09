package br.com.zupacademy.fpsaraiva.mercadolivre.model;

import br.com.zupacademy.fpsaraiva.mercadolivre.shared.SenhaLimpa;
import org.hibernate.validator.constraints.Length;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "sequence_id_usuarios"
    )
    @SequenceGenerator(
            name = "sequence_id_usuarios",
            sequenceName = "sequence_usuario",
            allocationSize = 1,
            initialValue = 1
    )
    private Long id;
    @NotBlank
    @Email
    @Column(nullable = false)
    private String login;
    @NotBlank
    @Length(min = 6)
    @Column(nullable = false)
    private String senha;
    @NotNull
    @PastOrPresent
    private LocalDateTime instanteCadastro = LocalDateTime.now();

    @Deprecated
    public Usuario() {}

    public Usuario(@NotBlank @Email String login, @Valid @NotNull SenhaLimpa senhaLimpa) {
        Assert.isTrue(StringUtils.hasLength(login), "Login não pode ser em branco.");
        Assert.notNull(senhaLimpa, "Objeto do tipo senha limpa não pode ser nulo.");

        this.login = login;
        this.senha = senhaLimpa.hash();
    }

}
