package br.com.zupacademy.fpsaraiva.mercadolivre.fechacompra;

import br.com.zupacademy.fpsaraiva.mercadolivre.model.Compra;

public interface EventoCompraSucesso {

    void processa(Compra compra);
}
