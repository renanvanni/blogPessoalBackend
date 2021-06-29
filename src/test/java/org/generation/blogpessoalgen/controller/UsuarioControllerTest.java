package org.generation.blogpessoalgen.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.generation.blogpessoalgen.model.UserLogin;
import org.generation.blogpessoalgen.model.Usuario;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsuarioControllerTest {

	@Autowired
	private TestRestTemplate testRestTemplate;
	
	private Usuario usuario;
	private Usuario usuarioupd;
	
	@BeforeAll
	public void start() {
		usuario = new Usuario("renan", "Renan", "12345");
		usuarioupd = new Usuario("Renan Gonçalves", "Renan Vanni", "12345");
	}
	
	
	@Test
	public void deveCadastrarUsuarios() {
		HttpEntity<Usuario> request = new HttpEntity<Usuario>(usuario);
		ResponseEntity<Usuario> resposta = testRestTemplate.exchange("/usuarios/cadastrar", HttpMethod.POST, request, Usuario.class);
				assertEquals(HttpStatus.CREATED, resposta.getStatusCode());
	}
	
	@Test
	public void deveLogarUsuarios() {
		UserLogin userLogin = new UserLogin(usuario);
		HttpEntity<UserLogin> request = new HttpEntity<UserLogin>(userLogin);
		ResponseEntity<Usuario> resposta = testRestTemplate.exchange("/usuarios/logar", HttpMethod.POST, request, Usuario.class);
				assertEquals(HttpStatus.OK, resposta.getStatusCode());
	}
	
	@Disabled
	@Test
	public void deveMostrarTodosUsuarios() {
		ResponseEntity<String> resposta = testRestTemplate.withBasicAuth("Renan Vanni", "12345")
				.exchange("/usuarios", HttpMethod.GET, null, String.class);
		assertEquals(HttpStatus.OK, resposta.getStatusCode());
	}
	
	@Test
	public void deveRealizarPutUsuarios() {
		HttpEntity<Usuario> request = new HttpEntity<Usuario>(usuarioupd);
		ResponseEntity<Usuario> resposta = testRestTemplate.exchange("/usuarios", HttpMethod.PUT, request, Usuario.class);
		assertEquals(HttpStatus.OK, resposta.getStatusCode());
	}
	
	@Disabled
	@Test
	public void deveRealizarDeleteUsuarios() {
		ResponseEntity<String> resposta = testRestTemplate.withBasicAuth("Maria", "65432")
				.exchange("/usuarios/7", HttpMethod.DELETE, null, String.class);
		assertEquals(HttpStatus.OK, resposta.getStatusCode());
	}
}
