package br.com.zupacademy.fpsaraiva.mercadolivre.dto;

import br.com.zupacademy.fpsaraiva.mercadolivre.model.Categoria;
import br.com.zupacademy.fpsaraiva.mercadolivre.repository.NovaCategoriaRepository;
import br.com.zupacademy.fpsaraiva.mercadolivre.shared.validacoes.ExisteId;
import br.com.zupacademy.fpsaraiva.mercadolivre.shared.validacoes.ValorUnico;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public class NovaCategoriaRequest {

    @NotBlank
    @ValorUnico(domainClass = Categoria.class, fieldName = "nome", message = "ERRO: Categoria já cadastrada no sistema.")
    private String nome;

    @Positive
    @ExisteId(domainClass = Categoria.class, fieldName = "id")
    private Long idCategoriaMae;

    public NovaCategoriaRequest(String nome, Long idCategoriaMae) {
        this.nome = nome;
        this.idCategoriaMae = idCategoriaMae;
    }

    public Categoria toModel(NovaCategoriaRepository novaCategoriaRepository) {
        if(idCategoriaMae != null){
            Categoria categoriaMae = novaCategoriaRepository.findById(idCategoriaMae).get();
            return new Categoria(nome, categoriaMae);
        }
        return new Categoria(nome);
    }

}