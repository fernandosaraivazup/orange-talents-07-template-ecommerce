package br.com.zupacademy.fpsaraiva.mercadolivre.shared;

import br.com.zupacademy.fpsaraiva.mercadolivre.model.Pergunta;
import org.springframework.stereotype.Component;

@Component
public class CentralDeEmail {

    public void enviaEmail(Pergunta pergunta) {
        System.out.println("Email: Olá, Vendedor, você recebeu uma pergunta!");
    }

}
