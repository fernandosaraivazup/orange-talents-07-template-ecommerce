package br.com.zupacademy.fpsaraiva.mercadolivre.config.security;

import br.com.zupacademy.fpsaraiva.mercadolivre.model.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

    @Value("${desafio-api-ml.jwt.expiration}")
    private String expiration;
    @Value("${desafio-api-ml.jwt.secret}")
    private String secret;

    public String gerarToken(Authentication authentication) {
        Usuario usuarioLogado = (Usuario) authentication.getPrincipal();
        Date momentoAtual = new Date();
        Date validadeToken = new Date(momentoAtual.getTime() + Long.parseLong(expiration));

        return Jwts.builder()
                .setIssuer("Desafio API do Mercado Livre")
                .setSubject(usuarioLogado.getId().toString())
                .setIssuedAt(momentoAtual)
                .setExpiration(validadeToken)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public boolean isTokenValido(String token) {
        try {
            Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Long getIdUsuario(String token) {
        Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
        return Long.parseLong(claims.getSubject());
    }

}
