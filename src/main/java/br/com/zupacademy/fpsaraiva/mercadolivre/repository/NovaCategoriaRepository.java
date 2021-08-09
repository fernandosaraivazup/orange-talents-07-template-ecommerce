package br.com.zupacademy.fpsaraiva.mercadolivre.repository;

import br.com.zupacademy.fpsaraiva.mercadolivre.model.Categoria;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NovaCategoriaRepository extends CrudRepository<Categoria, Long> {
}
