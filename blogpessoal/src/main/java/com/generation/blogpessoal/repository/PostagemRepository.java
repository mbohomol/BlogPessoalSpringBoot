package com.generation.blogpessoal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.generation.blogpessoal.model.Postagem;

//criação de uma interface "Repository"
@Repository // acessa o banco de dados e busca informações // interage com o banco de dados
public interface PostagemRepository extends JpaRepository <Postagem, Long>{ //<Nome do Repositório, ID do cliente>
	
	public List <Postagem> findAllByTituloContainingIgnoreCase(String titulo);

}
