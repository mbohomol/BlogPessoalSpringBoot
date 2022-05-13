
package com.generation.blogpessoal.controller;

import java.util.List;

import javax.validation.Valid;

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

import com.generation.blogpessoal.model.Postagem;
import com.generation.blogpessoal.repository.PostagemRepository;

@RestController // diz que é uma classe controladora Rest
@RequestMapping("/postagens") // indica que a URL da API desse controller começa com /categoria, ou seja:
								// http://localhost:8080/categoria
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PostagemController { // classe que devolve uma resposta

	@Autowired
	private PostagemRepository postagemRepository; // acessa todos os métodos da Interface, ou seja acessa todas as
													// postagens

	@GetMapping // lista todas as categorias
	public ResponseEntity<List<Postagem>> getAll() { // por issso que tem que criar uma classe "Postagens", para poder
														// importar
		return ResponseEntity.ok(postagemRepository.findAll()); // // findAll()= seleciona todas as postagens do
																// repository (select * from tb_postagens)

	}

	@GetMapping("/{id}") // lista apenas a categoria buscando pelo seu ID
	public ResponseEntity<Postagem> getById(@PathVariable Long id) { // @PathVariable Long id = insere o id = ex:
																		// http://localhost:8080/categoria/1
		return postagemRepository.findById(id).map(resposta -> ResponseEntity.ok(resposta))// se a postagem existir, a
																							// função map retorna o
																							// status OK => 200
				.orElse(ResponseEntity.notFound().build()); // se a postagem não for encontrada, retorna o status Not
															// Found => 404
	}

	@GetMapping("/titulo/{titulo}") // busca pelo nome de uma categoria
	public ResponseEntity<List<Postagem>> getByTitulo(@PathVariable String titulo) {// getbyName = busca pelo nome //
																					// @PathVariable adiciona o nome na
																					// url, ex:
																					// http://localhost:8080/categoria/nome
		return ResponseEntity.ok(postagemRepository.findAllByTituloContainingIgnoreCase(titulo)); // //findAll retorna
																									// uma lista de
																									// dados da tabela /
																									// select * from
																									// tb_postagens
																									// WHERE titulo LIKE
																									// "%titulo%";
	}

	@PostMapping // salva uma postagem
	public ResponseEntity<Postagem> postPostagem(@Valid @RequestBody Postagem postagem) {
		return ResponseEntity.status(HttpStatus.CREATED).body(postagemRepository.save(postagem));
	}

	@PutMapping // atualiza uma categoria
	public ResponseEntity<Postagem> putPostagem(@Valid @RequestBody Postagem postagem) {// findById = para obter um
																						// contato específico é utilizar
																						// o método = select * from
																						// contacts where id = X
		return ResponseEntity.status(HttpStatus.OK).body(postagemRepository.save(postagem));// se a postagem existir, a
																							// função map retorna o
																							// status OK => 200
	}

	@DeleteMapping("/{id}") // deleta uma categoria
	public void deletePostagem(@PathVariable Long id) { // deleteCategoria(@PathVariable Long id) é do tipo
														// ResponseEntity pq ele responde uma Requisição
		postagemRepository.deleteById(id); // se a categoria for encontrada e excluída da tabela aparecerá = Request
											// Response Status 204 => NO_CONTENT
											// caso não seja encontrada, a resposta será = Not Found => 404

	}
}
