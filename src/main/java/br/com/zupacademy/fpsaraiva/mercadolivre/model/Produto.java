package br.com.zupacademy.fpsaraiva.mercadolivre.model;

import br.com.zupacademy.fpsaraiva.mercadolivre.dto.NovaCaracteristicaRequest;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Produto {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "sequence_id_produtos"
    )
    @SequenceGenerator(
            name = "sequence_id_produtos",
            sequenceName = "sequence_produto",
            allocationSize = 1
    )
    @Column(name = "product_id")
    private Long id;

    @NotBlank
    private String nome;

    @NotNull
    @Positive
    private BigDecimal valor;

    @NotNull
    @PositiveOrZero
    private Integer quantidade;

    @Size(min = 3)
    @OneToMany(mappedBy = "produto", cascade = CascadeType.PERSIST)
    private Set<CaracteristicaProduto> caracteristicas = new HashSet<>();

    @NotBlank
    @Size(max = 1000)
    private String descricao;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "product_owner_id")
    private Usuario usuario;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "category")
    private Categoria categoria;

    @PastOrPresent
    @CreationTimestamp
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
    private List<ImagemProduto> imagens = new ArrayList<>();

    @OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
    private List<Opiniao> opinioes = new ArrayList<>();

    @Deprecated
    public Produto() {
    }

    public Produto(String nome, BigDecimal valor, Integer quantidade, String descricao, List<NovaCaracteristicaRequest> caracteristicas,
                   Categoria categoria, Usuario usuario) {
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        caracteristicas.forEach(caracteristica -> this.caracteristicas.add(caracteristica.toModel(this)));
        this.descricao = descricao;
        this.categoria = categoria;
        this.usuario = usuario;
        this.createdAt = LocalDateTime.now();
    }

    public void associaImagens(List<String> urls){
        List<ImagemProduto> imagens = urls.stream().map(url -> new ImagemProduto(url, this)).collect(Collectors.toList());
        this.imagens.addAll(imagens);
    }

    public boolean pertenceA(Usuario usuario) {
        return this.usuario.equals(usuario);
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void adicionaOpiniao(Opiniao opiniao) {
        this.opinioes.add(opiniao);
    }

}
