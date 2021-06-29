package org.generation.blogpessoalgen.controller;

import java.util.List;

import org.generation.blogpessoalgen.model.Postagem;
import org.generation.blogpessoalgen.repository.PostagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/postagens")//define a URL para o programa
@CrossOrigin(origins = "*", allowedHeaders = "*")//permite que o front-end trabalhe nesse programa, por isso SEMPRE tem que ter essa linha
public class PostagemController {

	@Autowired
	private PostagemRepository repository;
	
	@GetMapping
	public ResponseEntity<List<Postagem>> GetAll(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Postagem> GetById(@PathVariable long id){
		return repository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/title/{title}")//procura a palavra pelo titulo e depois da / vc escreve o que quer encontrar
	public ResponseEntity<List<Postagem>> GetByTitle(@PathVariable String title){
		return ResponseEntity.ok(repository.findAllByTitleContainingIgnoreCase(title));//esse item findAllByTitleContainingIgnoreCase foi criado na pasta PostagemRepository
	}
	
	@PostMapping//serve para postar algo no postman, por exemplo(e não pode passar o id no postman)
	public ResponseEntity<Postagem> postPostagem(@RequestBody Postagem postagem){
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(postagem));//utiliza o created para criar uma nova postagem e o body serve para dentro do corpo da mensagem ser salvo a nova postagem
	}
	
	@PutMapping//serve para atualizar algum dado de uma postagem anterior(e tem que passar o id no postman)
	public ResponseEntity<Postagem> putPostagem(@RequestBody Postagem postagem){
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(postagem));//utiliza o OK para encontrar na lista o id que queremos alterar e o body atualiza ela
	}
	
	@DeleteMapping("/{id}")//deleta através do id do item, por isso no postman tem que colocar /id que vc quer deletar
	public void deletePostagem(@PathVariable long id) {//por causa do void que não precisa de return na próxima linha e tem que ser long, pq na model id foi definido como long
		repository.deleteById(id);
	}
}
