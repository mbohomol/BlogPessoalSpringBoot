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

import com.generation.blogpessoal.model.Tema;
import com.generation.blogpessoal.repository.TemaRepository;

@RestController //diz que é uma classe controladora Rest
@RequestMapping("/tema") // mapeia os endpoints, para receber requisições com "/tema"
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TemaController {
	
	@Autowired
	private TemaRepository temaRepository;
	
	@GetMapping // para saber que esta chamando uma requisição, metódo que responde a requisição
	public ResponseEntity<List<Tema>> getAll(){ //por issso que tem que criar uma classe "Postagens", para poder importar
		return ResponseEntity.ok(temaRepository.findAll()); 	
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Tema> getById(@PathVariable Long id){
		return temaRepository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.notFound().build()); 
	}
	@GetMapping("/nome/{nome}")
	public ResponseEntity <List<Tema>>getByNome(@PathVariable String nome){
		return ResponseEntity.ok(temaRepository.findAllByTituloContainingIgnoreCase(nome)); 
	}
	
	@PostMapping
	public ResponseEntity <Tema> postPostagem(@Valid @RequestBody Tema tema){
		return ResponseEntity.status(HttpStatus.CREATED).body(temaRepository.save(tema));
	}
	
	@PutMapping
	public ResponseEntity <Tema> putPostagem(@Valid @RequestBody Tema tema){
		return ResponseEntity.status(HttpStatus.OK).body(temaRepository.save(tema));
	}
	@DeleteMapping("/{id}")
	public void deletePostagem(@PathVariable Long id) {
		temaRepository.deleteById(id);
	}
}



