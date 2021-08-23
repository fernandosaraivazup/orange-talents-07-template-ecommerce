package br.com.zupacademy.fpsaraiva.mercadolivre.sistemasterceiros;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class SistemasTerceirosController {

    @PostMapping("/notas-fiscais")
    public void criarNota(@Valid @RequestBody NovaCompraNFRequest request) throws InterruptedException {
        System.out.println("Criando nota " + request + ".");
        Thread.sleep(150);
    }

    @PostMapping("/ranking")
    public void rankearCompra(@Valid @RequestBody RankingNovaCompraRequest request) throws InterruptedException {
        System.out.println("Criando ranking " + request + ".");
        Thread.sleep(150);
    }

}
