package br.com.zupacademy.fpsaraiva.mercadolivre.shared.validacoes;

import br.com.zupacademy.fpsaraiva.mercadolivre.dto.CompraRequest;
import br.com.zupacademy.fpsaraiva.mercadolivre.model.Produto;
import br.com.zupacademy.fpsaraiva.mercadolivre.repository.NovoProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

public class ExisteQuantidadeValidator implements ConstraintValidator<ExisteQuantidade, CompraRequest> {

    @Autowired
    private NovoProdutoRepository produtoRepository;

    @Override
    public boolean isValid(CompraRequest compraRequest, ConstraintValidatorContext constraintValidatorContext) {
        Optional<Produto> possivelProduto = produtoRepository.findById(compraRequest.getProdutoId());

        if(possivelProduto.isEmpty()){
            return false;
        }

        Produto produto = possivelProduto.get();
        return produto.getQuantidade() >= compraRequest.getQuantidade() ? true : false;
    }

}
