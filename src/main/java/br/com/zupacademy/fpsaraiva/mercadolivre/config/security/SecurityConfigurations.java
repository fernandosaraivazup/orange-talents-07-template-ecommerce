package br.com.zupacademy.fpsaraiva.mercadolivre.config.security;

import br.com.zupacademy.fpsaraiva.mercadolivre.repository.NovoUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfigurations extends WebSecurityConfigurerAdapter {

    @Autowired
    private AutenticacaoService autenticacaoService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private NovoUsuarioRepository novoUsuarioRepository;

    //Para injetar no AutenticacaoController. Dispara manualmente o processo de autenticação
    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    //Configurações de autenticação
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(autenticacaoService).passwordEncoder(new BCryptPasswordEncoder());
    }

    //Configurações de autorização
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.POST, "/usuario").permitAll()
                .antMatchers(HttpMethod.POST, "/categoria").permitAll()
                .antMatchers(HttpMethod.POST, "/auth").permitAll() //libera método POST em /auth
                .anyRequest().authenticated() //requer auth endpoints não listados
                .and().csrf().disable() //para auth via token
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) //define auth stateless
                .and().addFilterBefore(new AutenticacaoViaTokenFilter(tokenService,novoUsuarioRepository), UsernamePasswordAuthenticationFilter.class); //filtro que autentica token recebido em cada requisição*/
    }

    //Configurações de recursos estáticos (js, css, imagens, etc.)
    @Override
    public void configure(WebSecurity web) throws Exception {}

}
