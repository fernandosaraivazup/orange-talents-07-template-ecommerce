package br.com.zupacademy.fpsaraiva.mercadolivre.fechacompra;

public enum StatusRetornoPagseguro {

    SUCESSO, ERRO;

    public StatusTransacao normaliza() {
        if(this.equals(SUCESSO)) {
            return StatusTransacao.sucesso;
        }

        return StatusTransacao.erro;
    }

}
