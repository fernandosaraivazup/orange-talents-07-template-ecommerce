package br.com.zupacademy.fpsaraiva.mercadolivre.fechacompra;

import br.com.zupacademy.fpsaraiva.mercadolivre.model.Compra;
import br.com.zupacademy.fpsaraiva.mercadolivre.model.Transacao;

public interface RetornoGatewayPagamento {

    Transacao toTransacao(Compra compra);
}
