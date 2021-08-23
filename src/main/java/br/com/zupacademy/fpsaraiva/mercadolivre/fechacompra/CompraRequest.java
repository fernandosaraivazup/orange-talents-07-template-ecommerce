package br.com.zupacademy.fpsaraiva.mercadolivre.fechacompra;

import br.com.zupacademy.fpsaraiva.mercadolivre.model.Compra;
import br.com.zupacademy.fpsaraiva.mercadolivre.model.Produto;
import br.com.zupacademy.fpsaraiva.mercadolivre.model.Usuario;
import br.com.zupacademy.fpsaraiva.mercadolivre.shared.validacoes.ExisteId;
import br.com.zupacademy.fpsaraiva.mercadolivre.shared.validacoes.ExisteQuantidade;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@ExisteQuantidade
public class CompraRequest {

    @NotNull
    @Positive
    private Integer quantidade;

    @NotNull
    @ExisteId(domainClass = Produto.class, fieldName = "id")
    private Long produtoId;

    @NotNull
    private GatewayPagamento gatewayEscolhido;

    public CompraRequest(Integer quantidade, Long produtoId, GatewayPagamento gatewayEscolhido) {
        this.quantidade = quantidade;
        this.produtoId = produtoId;
        this.gatewayEscolhido = gatewayEscolhido;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public Long getProdutoId() {
        return produtoId;
    }

    public GatewayPagamento getGateway() {
        return gatewayEscolhido;
    }

    public Compra toModel(Produto produtoASerComprado, Usuario comprador) {
        return new Compra(produtoASerComprado, quantidade, comprador, gatewayEscolhido, Status.iniciado);
    }

}
