package br.com.zupacademy.fpsaraiva.mercadolivre.dto;

import br.com.zupacademy.fpsaraiva.mercadolivre.fechacompra.RetornoGatewayPagamento;
import br.com.zupacademy.fpsaraiva.mercadolivre.fechacompra.StatusRetornoPagseguro;
import br.com.zupacademy.fpsaraiva.mercadolivre.model.Compra;
import br.com.zupacademy.fpsaraiva.mercadolivre.model.Transacao;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class RetornoPagseguroRequest implements RetornoGatewayPagamento {

    @NotBlank
    private String idTransacao;
    @NotNull
    private StatusRetornoPagseguro status;

    public RetornoPagseguroRequest(String idTransacao, StatusRetornoPagseguro status) {
        super();
        this.idTransacao = idTransacao;
        this.status = status;
    }

    @Override
    public String toString() {
        return "RetornoPagseguroRequest [idTransacao=" + idTransacao
                + ", status=" + status + "]";
    }

    public Transacao toTransacao(Compra compra) {
        return new Transacao(status.normaliza(),idTransacao,compra);
    }

}
