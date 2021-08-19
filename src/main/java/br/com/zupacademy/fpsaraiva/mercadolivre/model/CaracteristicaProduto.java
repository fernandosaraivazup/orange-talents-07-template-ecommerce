package br.com.zupacademy.fpsaraiva.mercadolivre.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class CaracteristicaProduto {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "sequence_id_produtoscaracs"
    )
    @SequenceGenerator(
            name = "sequence_id_produtoscaracs",
            sequenceName = "sequence_produtocaracs",
            allocationSize = 1
    )
    @Column(name = "product_caracteristics_id")
    private Long id;

    @NotBlank
    private String nome;

    @NotBlank
    private String descricao;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Produto produto;

    @Deprecated
    public CaracteristicaProduto() {
    }

    public CaracteristicaProduto(String nome, String descricao, Produto produto) {
        this.nome = nome;
        this.descricao = descricao;
        this.produto = produto;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

}
