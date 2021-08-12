package br.com.zupacademy.fpsaraiva.mercadolivre.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
public class Perfil implements GrantedAuthority {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "sequence_id_perfis"
    )
    @SequenceGenerator(
            name = "sequence_id_perfis",
            sequenceName = "sequence_perfil",
            allocationSize = 1,
            initialValue = 1
    )
    private Long id;
    private String nome;

    @Deprecated
    public Perfil() {}

    @Override
    public String getAuthority() {
        return nome;
    }
}
