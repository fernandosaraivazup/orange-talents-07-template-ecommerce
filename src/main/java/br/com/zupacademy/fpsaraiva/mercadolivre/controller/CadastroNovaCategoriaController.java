package br.com.zupacademy.fpsaraiva.mercadolivre.controller;

import br.com.zupacademy.fpsaraiva.mercadolivre.dto.NovaCategoriaRequest;
import br.com.zupacademy.fpsaraiva.mercadolivre.dto.NovaCategoriaResponse;
import br.com.zupacademy.fpsaraiva.mercadolivre.model.Categoria;
import br.com.zupacademy.fpsaraiva.mercadolivre.repository.NovaCategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
public class CadastroNovaCategoriaController {

    @Autowired
    private NovaCategoriaRepository novaCategoriaRepository;

    @PostMapping("/categoria")
    @Transactional
    public ResponseEntity<NovaCategoriaResponse> cadastraNovaCategoria(@Valid @RequestBody NovaCategoriaRequest novaCategoriaRequest) {
        Categoria novaCategoria = novaCategoriaRequest.toModel(novaCategoriaRepository);
        novaCategoriaRepository.save(novaCategoria);

        return ResponseEntity.ok().body(new NovaCategoriaResponse(novaCategoria));
    }

}
