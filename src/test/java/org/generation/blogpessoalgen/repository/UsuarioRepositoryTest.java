package org.generation.blogpessoalgen.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.generation.blogpessoalgen.model.Usuario;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsuarioRepositoryTest {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@BeforeAll
	public void start() {
		
		Usuario usuario = new Usuario("Renan", "Renan", "12345");
		
		if (usuarioRepository.findByUsuario(usuario.getUsuario()) != null) {
			usuarioRepository.save(usuario);
		}
		
		usuario = new Usuario("Lauro", "Lauro", "12354");
		if (usuarioRepository.findByUsuario(usuario.getUsuario()) != null) {
			usuarioRepository.save(usuario);
		}
		
		usuario = new Usuario("Rebeca", "Rebeca", "54321");
		if (usuarioRepository.findByUsuario(usuario.getUsuario()) != null) {
			usuarioRepository.save(usuario);
		}
		
		usuario = new Usuario("Luis", "Luis", "98765");
		if (usuarioRepository.findByUsuario(usuario.getUsuario()) != null) {
			usuarioRepository.save(usuario);
		}
	}
	
	@Test
	public void findByRetornaUsuario() throws Exception{
		Usuario usuario = usuarioRepository.findFirstByUsuario("Renan");
		
		assertTrue(usuario.getUsuario().equals("Renan"));
	}
	
	@Test
	public void findAllByUsuarioIgnoreCase(){
		List<Usuario> usuario = usuarioRepository.findAllByUsuarioContainingIgnoreCase("Renan");
		assertEquals(1, usuario.size());
	}
	
	@AfterAll
	public void end() {
		usuarioRepository.deleteAll();
	}
}
