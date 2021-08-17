package br.com.zupacademy.fpsaraiva.mercadolivre.controller;

import br.com.zupacademy.fpsaraiva.mercadolivre.dto.NovoProdutoRequest;
import br.com.zupacademy.fpsaraiva.mercadolivre.model.Produto;
import br.com.zupacademy.fpsaraiva.mercadolivre.model.Usuario;
import br.com.zupacademy.fpsaraiva.mercadolivre.repository.NovaCategoriaRepository;
import br.com.zupacademy.fpsaraiva.mercadolivre.repository.NovoProdutoRepository;
import br.com.zupacademy.fpsaraiva.mercadolivre.repository.NovoUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/produto")
public class CadastroProdutoController {

    @Autowired
    private NovoProdutoRepository novoProdutoRepository;

    @Autowired
    private NovaCategoriaRepository novaCategoriaRepository;

    @Autowired
    private NovoUsuarioRepository novoUsuarioRepository;

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody @Valid NovoProdutoRequest novoProdutoRequest, @AuthenticationPrincipal Usuario usuario){
        Produto produto = novoProdutoRequest.toModel(novaCategoriaRepository, usuario);

        novoProdutoRepository.save(produto);

        return ResponseEntity.ok().build();
    }

}