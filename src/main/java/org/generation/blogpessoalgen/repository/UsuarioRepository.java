package org.generation.blogpessoalgen.repository;

import java.util.List;
import java.util.Optional;

import org.generation.blogpessoalgen.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	public Optional<Usuario> findByUsuario(String usuario);
	
	public List<Usuario> findAllByUsuarioContainingIgnoreCase(String usuario);
	
	public Usuario findFirstByUsuario(String usuario);
	
}
