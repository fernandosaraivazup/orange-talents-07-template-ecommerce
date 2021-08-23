package br.com.zupacademy.fpsaraiva.mercadolivre.model;

import br.com.zupacademy.fpsaraiva.mercadolivre.fechacompra.GatewayPagamento;
import br.com.zupacademy.fpsaraiva.mercadolivre.fechacompra.Status;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
public class Compra {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "sequence_id_compras"
    )
    @SequenceGenerator(
            name = "sequence_id_compras",
            sequenceName = "sequence_compra",
            allocationSize = 1
    )
    @Column(name = "purchase_id")
    private Long id;

    @ManyToOne
    @NotNull
    @Valid
    private Produto produtoEscolhido;

    @Positive
    @Column(name = "quantity")
    private int quantidade;

    @ManyToOne
    @NotNull
    @Valid
    private Usuario comprador;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "gateway")
    private GatewayPagamento gatewayEscolhido;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Status status;

    public Compra(Produto produtoASerComprado, int quantidade, Usuario comprador,
                  GatewayPagamento gatewayEscolhido, Status status) {
        this.produtoEscolhido = produtoASerComprado;
        this.quantidade = quantidade;
        this.comprador = comprador;
        this.gatewayEscolhido = gatewayEscolhido;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public String urlRedirecionamento(UriComponentsBuilder uriComponentsBuilder) {
        return this.gatewayEscolhido.criaUrlRetorno(this, uriComponentsBuilder);
    }

}