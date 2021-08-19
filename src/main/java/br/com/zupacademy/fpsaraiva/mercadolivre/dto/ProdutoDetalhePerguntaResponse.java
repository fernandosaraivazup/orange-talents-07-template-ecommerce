package br.com.zupacademy.fpsaraiva.mercadolivre.dto;

import br.com.zupacademy.fpsaraiva.mercadolivre.model.Pergunta;

import java.time.LocalDateTime;

public class ProdutoDetalhePerguntaResponse {

    private String titulo;

    private LocalDateTime instanteCriacao;

    public ProdutoDetalhePerguntaResponse(Pergunta pergunta) {
        this.titulo = pergunta.getTitulo();
        this.instanteCriacao = pergunta.getInstanteCriacao();
    }

    public String getTitulo() {
        return titulo;
    }

    public LocalDateTime getInstanteCriacao() {
        return instanteCriacao;
    }

}
