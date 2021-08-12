package br.com.zupacademy.fpsaraiva.mercadolivre.config.security;

import br.com.zupacademy.fpsaraiva.mercadolivre.model.Usuario;
import br.com.zupacademy.fpsaraiva.mercadolivre.repository.NovoUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AutenticacaoService implements UserDetailsService {

    @Autowired
    private NovoUsuarioRepository novoUsuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<Usuario> usuario = novoUsuarioRepository.findByLogin(login);

        if(usuario.isPresent()) {
            return usuario.get();
        }

        throw new UsernameNotFoundException("Dados de usuário inválidos!");
    }

}
