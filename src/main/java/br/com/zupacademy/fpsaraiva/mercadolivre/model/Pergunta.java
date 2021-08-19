package br.com.zupacademy.fpsaraiva.mercadolivre.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;

@Entity
public class Pergunta {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "sequence_id_perguntas"
    )
    @SequenceGenerator(
            name = "sequence_id_perguntas",
            sequenceName = "sequence_pergunta",
            allocationSize = 1
    )
    @Column(name = "question_id")
    private Long id;

    @NotBlank
    private String titulo;

    @NotNull
    @PastOrPresent
    private LocalDateTime instanteCriacao;

    @NotNull
    @ManyToOne
    private Usuario usuario;

    @NotNull
    @ManyToOne
    private Produto produto;

    @Deprecated
    public Pergunta(){}

    public Pergunta(String titulo, Usuario usuario, Produto produto) {
        this.titulo = titulo;
        this.instanteCriacao = LocalDateTime.now();
        this.usuario = usuario;
        this.produto = produto;
    }

    public String getTitulo() {
        return titulo;
    }

    public LocalDateTime getInstanteCriacao() {
        return instanteCriacao;
    }

}
