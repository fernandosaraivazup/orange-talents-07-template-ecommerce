package br.com.zupacademy.fpsaraiva.mercadolivre.dto;

import br.com.zupacademy.fpsaraiva.mercadolivre.model.CaracteristicaProduto;

public class ProdutoDetalheCaracteristicaResponse {

    private String nome;

    private String descricao;

    public ProdutoDetalheCaracteristicaResponse(CaracteristicaProduto caracteristicaProduto){
        this.nome = caracteristicaProduto.getNome();
        this.descricao = caracteristicaProduto.getDescricao();
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

}
