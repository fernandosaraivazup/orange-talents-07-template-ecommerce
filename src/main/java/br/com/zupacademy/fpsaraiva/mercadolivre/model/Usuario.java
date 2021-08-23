package br.com.zupacademy.fpsaraiva.mercadolivre.model;

import br.com.zupacademy.fpsaraiva.mercadolivre.shared.senha.SenhaLimpa;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
public class Usuario implements UserDetails {

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
    private LocalDateTime instanteCadastro;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Perfil> perfil = new ArrayList<>();

    @Deprecated
    public Usuario() {}

    public Usuario(@NotBlank @Email String login, @Valid @NotNull SenhaLimpa senhaLimpa) {
        Assert.isTrue(StringUtils.hasLength(login), "Login não pode ser em branco.");
        Assert.notNull(senhaLimpa, "Objeto do tipo senha limpa não pode ser nulo.");

        this.login = login;
        this.senha = senhaLimpa.hash();
        this.instanteCadastro = LocalDateTime.now();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.perfil;
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(id, usuario.id) && Objects.equals(login, usuario.login) && Objects.equals(senha, usuario.senha);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, senha);
    }

}
