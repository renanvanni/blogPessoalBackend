package org.generation.blogpessoalgen.model;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class UsuarioTest {

	public Usuario usuario;
	
	@Autowired
	private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
	
	@BeforeEach
	public void start() {
		
		usuario = new Usuario("Renan", "Renan", "12345");
	}
	
	@Disabled
	@Test
	public void testValidationAtributos() {
		
		usuario.setNome("Lauro");
		usuario.setUsuario("Lauro");
		usuario.setSenha("32154");
		
		Set<ConstraintViolation<Usuario>> violations = validator.validate(usuario);
		System.out.println(violations.toString());
		assertTrue(violations.isEmpty());
	}
	
	@Test
	public void testValidationAtributosNulos() {
		
		Usuario usuarioErro = new Usuario();
		
		usuarioErro.setNome("Lauro");
		usuarioErro.setUsuario("Lauro");
		usuarioErro.setSenha("32154");
		
		Set<ConstraintViolation<Usuario>> violations = validator.validate(usuarioErro);
		System.out.println(violations.toString());
		assertTrue(violations.isEmpty());
	}
}
