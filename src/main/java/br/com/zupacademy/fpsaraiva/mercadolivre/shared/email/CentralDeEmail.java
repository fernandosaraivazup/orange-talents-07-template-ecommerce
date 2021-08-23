package br.com.zupacademy.fpsaraiva.mercadolivre.shared.email;

import br.com.zupacademy.fpsaraiva.mercadolivre.model.Compra;
import br.com.zupacademy.fpsaraiva.mercadolivre.model.Pergunta;
import org.springframework.stereotype.Component;

@Component
public class CentralDeEmail {

    public void enviaEmail(Pergunta pergunta) {
        System.out.println("Email: Olá, Vendedor, você recebeu uma pergunta!");
    }

    public void enviaEmailCompra(Compra novaCompra) {
        System.out.println("Email: Parabéns, Vendedor, você efetuou uma VENDA!");
    }

}
