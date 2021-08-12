package br.com.zupacademy.fpsaraiva.mercadolivre.controller;

import br.com.zupacademy.fpsaraiva.mercadolivre.config.security.TokenService;
import br.com.zupacademy.fpsaraiva.mercadolivre.dto.LoginRequest;
import br.com.zupacademy.fpsaraiva.mercadolivre.dto.TokenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager authManager;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/auth")
    public ResponseEntity<TokenResponse> autenticarUsuario(@Valid @RequestBody LoginRequest loginRequest) {
        UsernamePasswordAuthenticationToken dadosLogin = loginRequest.converter();

        try {
            Authentication authentication = authManager.authenticate(dadosLogin); //autentica usuário
            String token = tokenService.gerarToken(authentication); //gera token

            return ResponseEntity.ok(new TokenResponse(token, "Bearer"));
        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().build();
        }
    }

}