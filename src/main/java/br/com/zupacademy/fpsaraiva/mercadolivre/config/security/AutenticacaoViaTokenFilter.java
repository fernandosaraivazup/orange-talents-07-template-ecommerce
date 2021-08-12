package br.com.zupacademy.fpsaraiva.mercadolivre.config.security;

import br.com.zupacademy.fpsaraiva.mercadolivre.model.Usuario;
import br.com.zupacademy.fpsaraiva.mercadolivre.repository.NovoUsuarioRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AutenticacaoViaTokenFilter extends OncePerRequestFilter {

    private TokenService tokenService;
    private NovoUsuarioRepository novoUsuarioRepository;

    //Não posso injetar via Autowired TokenService e novoUsuarioRepository
    public AutenticacaoViaTokenFilter(TokenService tokenService, NovoUsuarioRepository novoUsuarioRepository) {
        this.tokenService = tokenService;
        this.novoUsuarioRepository = novoUsuarioRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = recuperarToken(request); //recebe token passado cliente

        boolean valido = tokenService.isTokenValido(token); //autentica o token
        if (valido) {
            autenticarCliente(token);
        }

        filterChain.doFilter(request, response);
    }

    private String recuperarToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");

        if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
            return null;
        }

        return token.substring(7, token.length());
    }

    private void autenticarCliente(String token) {
        Long idUsuario = tokenService.getIdUsuario(token);
        Usuario usuario = novoUsuarioRepository.findById(idUsuario).get();

        UsernamePasswordAuthenticationToken authentication = new
                UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authentication); //considera usuário autenticado
    }

}