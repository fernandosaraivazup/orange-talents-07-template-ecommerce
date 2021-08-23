package br.com.zupacademy.fpsaraiva.mercadolivre.model;

import br.com.zupacademy.fpsaraiva.mercadolivre.fechacompra.GatewayPagamento;
import br.com.zupacademy.fpsaraiva.mercadolivre.fechacompra.RetornoGatewayPagamento;
import br.com.zupacademy.fpsaraiva.mercadolivre.fechacompra.Status;
import io.jsonwebtoken.lang.Assert;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

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

    @OneToMany(mappedBy = "compra", cascade = CascadeType.MERGE)
    private Set<Transacao> transacoes = new HashSet<>();

    @Deprecated
    public Compra() {}

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

    public void adicionaTransacao(@Valid RetornoGatewayPagamento request) {
        Transacao novaTransacao = request.toTransacao(this);

        Assert.isTrue(!this.transacoes.contains(novaTransacao),
                "Já existe uma transacao igual a essa processada "
                        + novaTransacao);

        Set<Transacao> transacoesConcluidasComSucesso = this.transacoes.stream()
                .filter(Transacao::concluidaComSucesso)
                .collect(Collectors.toSet());

        Assert.isTrue(transacoesConcluidasComSucesso.isEmpty(),"Esse compra já foi concluída com sucesso");

        this.transacoes.add(novaTransacao);
    }

    @Override
    public String toString() {
        return "Compra{" +
                "id=" + id +
                ", produtoEscolhido=" + produtoEscolhido +
                ", quantidade=" + quantidade +
                ", comprador=" + comprador +
                ", gatewayEscolhido=" + gatewayEscolhido +
                ", status=" + status +
                ", transacoes=" + transacoes +
                '}';
    }

    private Set<Transacao> transacoesConcluidasComSucesso() {
        Set<Transacao> transacoesConcluidasComSucesso = this.transacoes.stream()
                .filter(Transacao::concluidaComSucesso)
                .collect(Collectors.toSet());

        Assert.isTrue(transacoesConcluidasComSucesso.size() <= 1,"ERRO: Existe mais de uma transacao concluída com sucesso aqui na compra " + this.id);

        return transacoesConcluidasComSucesso;
    }

    public boolean processadaComSucesso() {
        return !transacoesConcluidasComSucesso().isEmpty();
    }

    public Usuario getComprador() {
        return comprador;
    }

    public Usuario getDonoProduto() {
        return produtoEscolhido.getDono();
    }

}