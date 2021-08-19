package br.com.zupacademy.fpsaraiva.mercadolivre.controller;

import br.com.zupacademy.fpsaraiva.mercadolivre.dto.ProdutoDetalheResponse;
import br.com.zupacademy.fpsaraiva.mercadolivre.shared.CentralDeEmail;
import br.com.zupacademy.fpsaraiva.mercadolivre.model.Pergunta;
import br.com.zupacademy.fpsaraiva.mercadolivre.repository.PerguntaRepository;
import br.com.zupacademy.fpsaraiva.mercadolivre.dto.PerguntaRequest;
import br.com.zupacademy.fpsaraiva.mercadolivre.model.Opiniao;
import br.com.zupacademy.fpsaraiva.mercadolivre.repository.OpiniaoRepository;
import br.com.zupacademy.fpsaraiva.mercadolivre.dto.OpiniaoRequest;
import br.com.zupacademy.fpsaraiva.mercadolivre.dto.NovoProdutoRequest;
import br.com.zupacademy.fpsaraiva.mercadolivre.model.Produto;
import br.com.zupacademy.fpsaraiva.mercadolivre.model.Usuario;
import br.com.zupacademy.fpsaraiva.mercadolivre.repository.NovaCategoriaRepository;
import br.com.zupacademy.fpsaraiva.mercadolivre.repository.NovoProdutoRepository;
import br.com.zupacademy.fpsaraiva.mercadolivre.repository.NovoUsuarioRepository;
import br.com.zupacademy.fpsaraiva.mercadolivre.dto.ImagemRequest;
import br.com.zupacademy.fpsaraiva.mercadolivre.uploadimagens.UploaderImagens;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private NovoProdutoRepository novoProdutoRepository;

    @Autowired
    private NovaCategoriaRepository novaCategoriaRepository;

    @Autowired
    private NovoUsuarioRepository novoUsuarioRepository;

    @Autowired
    private UploaderImagens uploaderImagens;

    //Me parece que não está sendo mais usado - deprecated
    //@Autowired
    //EntityManager manager;

    @Autowired
    private OpiniaoRepository opiniaoRepository;

    @Autowired
    private PerguntaRepository perguntaRepository;

    @Autowired
    private CentralDeEmail centralDeEmail;

    @PostMapping
    public ResponseEntity<?> cadastrarProduto(@RequestBody @Valid NovoProdutoRequest novoProdutoRequest, @AuthenticationPrincipal Usuario usuario){
        Produto produto = novoProdutoRequest.toModel(novaCategoriaRepository, usuario);

        novoProdutoRepository.save(produto);

        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/{id}/imagens")
    public ResponseEntity<?> adicionarImagens(@PathVariable @NotNull Long id, @Valid ImagemRequest imagemRequest, @AuthenticationPrincipal Usuario usuario) {
        List<String> linksImagens = uploaderImagens.upload(imagemRequest.getImagens());
        Optional<Produto> produtoBuscado = novoProdutoRepository.findById(id);

        if (produtoBuscado.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        Produto produto = produtoBuscado.get();

        if (!produto.pertenceA(usuario)) {
            return ResponseEntity.badRequest().build();
        }

        produto.associaImagens(linksImagens);
        novoProdutoRepository.save(produto);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/opiniao")
    public ResponseEntity<?> adicionarOpiniao(@PathVariable @NotNull Long id, @RequestBody @Valid OpiniaoRequest opiniaoRequest, @AuthenticationPrincipal Usuario usuario) {
        Optional<Usuario> usuarioLogado = novoUsuarioRepository.findByLogin(usuario.getUsername());
        Optional<Produto> produtoBuscado = novoProdutoRepository.findById(id);

        if(produtoBuscado.isEmpty()){
            return ResponseEntity.badRequest().build();
        }

        Produto produto = produtoBuscado.get();
        Opiniao opiniao = opiniaoRequest.toModel(usuarioLogado.get(), produto);
        opiniaoRepository.save(opiniao);
        produto.adicionaOpiniao(opiniao);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/pergunta")
    public ResponseEntity<?> adicionarPergunta(@PathVariable @NotNull Long id, @RequestBody @Valid PerguntaRequest perguntaRequest,
                                    @AuthenticationPrincipal Usuario usuario) {
        Optional<Usuario> usuarioLogado = novoUsuarioRepository.findByLogin(usuario.getUsername());
        Optional<Produto> produtoBuscado = novoProdutoRepository.findById(id);

        if(produtoBuscado.isEmpty()){
            return ResponseEntity.badRequest().build();
        }

        Produto produto = produtoBuscado.get();

        Pergunta pergunta = perguntaRequest.toModel(usuarioLogado.get(), produto);
        perguntaRepository.save(pergunta);
        centralDeEmail.enviaEmail(pergunta);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDetalheResponse> detalharProduto(@PathVariable Long id){
        Optional<Produto> produtoBuscado = novoProdutoRepository.findById(id);

        if(produtoBuscado.isEmpty()){
            return ResponseEntity.badRequest().build();
        }

        Produto produto = produtoBuscado.get();

        return ResponseEntity.ok().body(new ProdutoDetalheResponse(produto));
    }

}
