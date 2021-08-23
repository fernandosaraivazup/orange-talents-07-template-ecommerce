package br.com.zupacademy.fpsaraiva.mercadolivre.sistemasterceiros;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SistemasTerceirosController {

    @PostMapping("/notas-fiscais")
    public void criarNota(Long idCompra,Long idComprador) throws InterruptedException {
        System.out.println("Criando nota para " + idCompra + " do comprador " + idComprador);
        Thread.sleep(150);
    }

    @PostMapping("/ranking")
    public void rankearCompra(Long idCompra,Long idVendedor) throws InterruptedException {
        System.out.println("Criando nota para " + idCompra + " do vendedor " + idVendedor);
        Thread.sleep(150);
    }

}
