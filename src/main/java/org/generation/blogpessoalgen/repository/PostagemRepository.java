package org.generation.blogpessoalgen.repository;

import java.util.List;
import java.util.Optional;

import org.generation.blogpessoalgen.model.Postagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository//serve para avisar que essa pasta é um repositório
public interface PostagemRepository extends JpaRepository<Postagem, Long>{//postagem é da classe model e long tem que ser escrito Long no estilo primitivo

	public List<Postagem> findAllByTitleContainingIgnoreCase(String title);//containing serve como o like e tráz tudo o que essa variável title tem no banco de dados/IgnoreCase serve para não ligar se as letras são maiúsculas ou minusculas
	
	public Optional <Postagem> findFirstByTituloIgnoreCase(String titulo);
	
	/**
	 * 
	 * A annotation @Query permite executar um consulta SQL nativa 
	 * (igual as consultas criadas no MySQL), dentro do Spring, onde:
	 * 
	 * Value -> A consulta propriamente dita. Observe que o parâmetro deve 
	 * ser precedido por : (:id), para indicar que é uma parâmetro passado
	 * via método
	 * 
	 * nativeQuery = true -> Indica que a consulta está no padrão SQL
	 * 
	 * O Método countPosts2 executará consulta e retornará um numero
	 * inteiro (total de Posts do tema_id informado), onde:
	 * 
	 * @Param("id") -> mapeia o parâmetro da consulta sql (:id)
	 * 
	 * long id -> é o parâmetro que será passado no método
	 * 
	 */

	@Query(value = "select count(tema_id) from tb_postagens where tema_id = :id", nativeQuery = true)
	public int countPosts(@Param("id") long id);
}
