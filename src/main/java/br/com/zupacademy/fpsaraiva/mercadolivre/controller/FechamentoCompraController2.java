package br.com.zupacademy.fpsaraiva.mercadolivre.controller;

import br.com.zupacademy.fpsaraiva.mercadolivre.dto.RetornoPagseguroRequest;
import br.com.zupacademy.fpsaraiva.mercadolivre.dto.RetornoPaypalRequest;
import br.com.zupacademy.fpsaraiva.mercadolivre.fechacompra.RetornoGatewayPagamento;
import br.com.zupacademy.fpsaraiva.mercadolivre.model.Compra;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
public class FechamentoCompraController2 {

    @PersistenceContext
    private EntityManager manager;

    @PostMapping("/retorno-pagseguro/{id}")
    @Transactional
    public String processamentoPagSeguro(@PathVariable("id") Long idCompra, @Valid RetornoPagseguroRequest request) {
        return processa(idCompra, request);
    }

    @PostMapping("/retorno-paypal/{id}")
    @Transactional
    public String processamentoPaypal(@PathVariable("id") Long idCompra, @Valid RetornoPaypalRequest request) {
        return processa(idCompra, request);
    }

    private String processa(Long idCompra, RetornoGatewayPagamento retornoGatewayPagamento) {
        Compra compra = manager.find(Compra.class, idCompra);

        compra.adicionaTransacao(retornoGatewayPagamento);
        manager.merge(compra);

        return compra.toString();
    }

}
