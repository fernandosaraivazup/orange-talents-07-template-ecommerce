package br.com.zupacademy.fpsaraiva.mercadolivre.shared.validacoes;

import br.com.zupacademy.fpsaraiva.mercadolivre.dto.NovoProdutoRequest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CaracteristicaUnicaValidator implements ConstraintValidator<CaracteristicaUnica, NovoProdutoRequest> {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public void initialize(CaracteristicaUnica constraintAnnotation) {
    }

    @Override
    public boolean isValid(NovoProdutoRequest produtoRequest, ConstraintValidatorContext constraintValidatorContext) {
        return !produtoRequest.temCaracteristicasIguais();
    }

}