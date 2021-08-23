package br.com.zupacademy.fpsaraiva.mercadolivre.shared.validacoes;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = {ExisteQuantidadeValidator.class})
@Target({TYPE})
@Retention(RUNTIME)
public @interface ExisteQuantidade {

    String message() default "Não existe a quantidade desejada em estoque";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
