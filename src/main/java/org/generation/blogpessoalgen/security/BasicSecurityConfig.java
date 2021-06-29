package org.generation.blogpessoalgen.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class BasicSecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
		/** 
		 * Autenticação em memória: Cria um Usuário e Senha para testes,
		 * disoensando a necessidade de cadastrar um usuário no Banco de Dados.
		 */
		auth.inMemoryAuthentication()
		.withUser("root")
		.password(passwordEncoder().encode("root"))
		.authorities("ROLE_USER"); //pode ser role_admin
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override //sobrescreve / através dessa linha o usuario não precisa colocar token para entrar no blog / stateless serve para não guardar a sessão
	protected void configure(HttpSecurity http) throws Exception{
		/**
		 * antMatchers().permitAll() -> Define os endpoints que serão utilizados
		 * sem autenticação (Login)
		 * 
		 * sessionCreationPolicy(SessionCreationPolicy.STATELESS) -> Informa que a nossa
		 * API é do tipo Stateless, ou seja, não guarda Sessões para o Cliente
		 * 
		 * cors() -> Cross Origins: Libera acessa  de outras origens, como o front-end
		 * 
		 * csrf().disabled() -> Desabilita o Token CSRF. Um token CSRF é um valor exclusivo, 
		 * secreto e imprevisível criado pelo aplicativo do lado do servidor e transmitido 
		 * ao cliente em uma requisição HTTP, para inibir a falsificação de solicitações. 
		 * Como utilizamos um Token de autenticação, não é necessário manter esta opção
		 * habilitada.
		 */
		http.authorizeRequests()
		.antMatchers("/usuarios/logar").permitAll()
		.antMatchers("/usuarios/cadastrar").permitAll()
		.anyRequest().authenticated()
		.and().httpBasic()
		.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and().cors()
		.and().csrf().disable();
	}
}
