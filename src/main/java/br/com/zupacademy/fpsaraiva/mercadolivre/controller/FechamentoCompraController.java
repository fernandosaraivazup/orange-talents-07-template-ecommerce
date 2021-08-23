package br.com.zupacademy.fpsaraiva.mercadolivre.controller;

import br.com.zupacademy.fpsaraiva.mercadolivre.fechacompra.CompraRequest;
import br.com.zupacademy.fpsaraiva.mercadolivre.fechacompra.GatewayPagamento;
import br.com.zupacademy.fpsaraiva.mercadolivre.model.Compra;
import br.com.zupacademy.fpsaraiva.mercadolivre.model.Produto;
import br.com.zupacademy.fpsaraiva.mercadolivre.model.Usuario;
import br.com.zupacademy.fpsaraiva.mercadolivre.repository.NovoUsuarioRepository;
import br.com.zupacademy.fpsaraiva.mercadolivre.shared.email.CentralDeEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
public class FechamentoCompraController {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private NovoUsuarioRepository usuarioRepository;

    @Autowired
    private CentralDeEmail centralDeEmail;

    @PostMapping("/compras")
    @Transactional
    public String comprarProduto(@RequestBody @Valid CompraRequest compraRequest,
                                 @AuthenticationPrincipal Usuario usuario,
                                 UriComponentsBuilder uriComponentsBuilder) {
        Produto produtoASerComprado = manager.find(Produto.class, compraRequest.getProdutoId());
        int quantidade = compraRequest.getQuantidade();
        boolean estoqueAbatido = produtoASerComprado.abataEstoque(quantidade);

        if (estoqueAbatido) {
            Usuario comprador = usuarioRepository.findByLogin(usuario.getUsername()).get();
            GatewayPagamento gateway = compraRequest.getGateway();

            Compra novaCompra = compraRequest.toModel(produtoASerComprado, comprador);
            manager.persist(novaCompra);
            centralDeEmail.enviaEmailCompra(novaCompra);

            return novaCompra.urlRedirecionamento(uriComponentsBuilder);
        }

        return "Ocorreu um problema. Não foi possível efetuar a compra.";
    }

}
