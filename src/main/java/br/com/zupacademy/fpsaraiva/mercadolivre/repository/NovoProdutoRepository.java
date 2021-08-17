package br.com.zupacademy.fpsaraiva.mercadolivre.repository;

import br.com.zupacademy.fpsaraiva.mercadolivre.model.Produto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NovoProdutoRepository extends CrudRepository<Produto, Long> {
}
