package br.com.zupacademy.fpsaraiva.mercadolivre.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Categoria {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "sequence_id_categorias"
    )
    @SequenceGenerator(
            name = "sequence_id_categorias",
            sequenceName = "sequence_categoria",
            allocationSize = 1,
            initialValue = 1
    )
    private Long id;
    @NotBlank
    @Column(nullable = false)
    private String nome;
    @ManyToOne
    private Categoria categoria;

    @Deprecated
    public Categoria(){}

    public Categoria(String nome) {
        this.nome = nome;
    }

    public Categoria(String nome, Categoria categoria) {
        this.nome = nome;
        this.categoria = categoria;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

}
