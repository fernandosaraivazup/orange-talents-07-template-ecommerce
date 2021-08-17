package br.com.zupacademy.fpsaraiva.mercadolivre.dto;

import br.com.zupacademy.fpsaraiva.mercadolivre.model.Categoria;
import br.com.zupacademy.fpsaraiva.mercadolivre.model.Produto;
import br.com.zupacademy.fpsaraiva.mercadolivre.model.Usuario;
import br.com.zupacademy.fpsaraiva.mercadolivre.repository.NovaCategoriaRepository;
import br.com.zupacademy.fpsaraiva.mercadolivre.shared.CaracteristicaUnica;
import br.com.zupacademy.fpsaraiva.mercadolivre.shared.ExisteId;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.*;

@CaracteristicaUnica
public class NovoProdutoRequest {

    @NotBlank
    private String nome;

    @NotNull
    @Positive
    private BigDecimal valor;

    @NotNull
    @PositiveOrZero
    private Integer quantidade;

    @Size(min = 3)
    @Valid
    private List<NovaCaracteristicaRequest> caracteristicas = new ArrayList<>();

    @NotBlank
    @Size(max = 1000)
    private String descricao;

    @NotNull
    @ExisteId(domainClass = Categoria.class, fieldName = "id", message = "ERRO: Categoria não existe.")
    private Long idCategoria;

    public NovoProdutoRequest(String nome, BigDecimal valor, Integer quantidade, List<NovaCaracteristicaRequest> caracteristicas,
                          String descricao, Long idCategoria) {
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.caracteristicas.addAll(caracteristicas);
        this.descricao = descricao;
        this.idCategoria = idCategoria;
    }

    public List<NovaCaracteristicaRequest> getCaracteristicas() {
        return caracteristicas;
    }

    public boolean temCaracteristicasIguais() {
        List<String> caracteristicasNomesCompara = new ArrayList<>();

        for (NovaCaracteristicaRequest caracteristica : caracteristicas) {
            if (caracteristicasNomesCompara.contains(caracteristica.getNome())) {
                return true;
            }
            caracteristicasNomesCompara.add(caracteristica.getNome());
        }
        return false;
    }

    public Produto toModel(NovaCategoriaRepository categoriaRepository, Usuario usuario) {
        Categoria categoria = categoriaRepository.findById(idCategoria).get();
        return new Produto(nome, valor, quantidade, descricao, caracteristicas, categoria, usuario);
    }

}