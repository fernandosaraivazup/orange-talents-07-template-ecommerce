package br.com.zupacademy.fpsaraiva.mercadolivre.sistemasterceiros;

import br.com.zupacademy.fpsaraiva.mercadolivre.fechacompra.EventoCompraSucesso;
import br.com.zupacademy.fpsaraiva.mercadolivre.model.Compra;
import io.jsonwebtoken.lang.Assert;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class NotaFiscal implements EventoCompraSucesso {

    @Override
    public void processa(Compra compra) {
        Assert.isTrue(compra.processadaComSucesso(),"ERRO: Compra não foi concluída com sucesso " + compra);

        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> request = Map.of("idCompra", compra.getId(),
                "idComprador", compra.getComprador().getId());

        restTemplate.postForEntity("http://localhost:8080/notas-fiscais",
                request, String.class);
    }

}
