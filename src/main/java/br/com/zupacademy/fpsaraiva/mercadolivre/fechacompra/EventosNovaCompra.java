package br.com.zupacademy.fpsaraiva.mercadolivre.fechacompra;

import br.com.zupacademy.fpsaraiva.mercadolivre.model.Compra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class EventosNovaCompra {

    @Autowired
    private Set<EventoCompraSucesso> eventosCompraSucesso;

    public void processa(Compra compra) {
        if(compra.processadaComSucesso()) {
            eventosCompraSucesso.forEach(evento -> evento.processa(compra));
        } else {
            //processar eventos de falha
        }
    }

}
