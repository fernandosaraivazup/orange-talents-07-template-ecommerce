package br.com.zupacademy.fpsaraiva.mercadolivre.repository;

import br.com.zupacademy.fpsaraiva.mercadolivre.model.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NovoUsuarioRepository extends CrudRepository<Usuario, Long> {
}
