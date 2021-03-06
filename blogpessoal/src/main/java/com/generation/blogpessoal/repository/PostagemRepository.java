package com.generation.blogpessoal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.generation.blogpessoal.model.Postagem;

//criação de uma interface "Repository"
@Repository //@Repository: indica que a Interface é do tipo repositório, ou seja, é responsável pela comunicação com 
//o Banco de dados através dos métodos padrão e das Method Queries
public interface PostagemRepository extends JpaRepository <Postagem, Long>{ //<Nome do Repositório, ID do cliente>
	
	public List <Postagem> findAllByTituloContainingIgnoreCase(String titulo);

}
