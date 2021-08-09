package br.com.zupacademy.fpsaraiva.mercadolivre.controller;

import br.com.zupacademy.fpsaraiva.mercadolivre.dto.NovoUsuarioRequest;
import br.com.zupacademy.fpsaraiva.mercadolivre.model.Usuario;
import br.com.zupacademy.fpsaraiva.mercadolivre.repository.NovoUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class CadastroNovoUsuarioController {

    @Autowired
    private NovoUsuarioRepository novoUsuarioRepository;

    @PostMapping("/usuario")
    public void cadastraNovoUsuario(@Valid @RequestBody NovoUsuarioRequest novoUsuarioRequest) {
        Usuario novoUsuario = novoUsuarioRequest.toModel();
        novoUsuarioRepository.save(novoUsuario);
    }

}
