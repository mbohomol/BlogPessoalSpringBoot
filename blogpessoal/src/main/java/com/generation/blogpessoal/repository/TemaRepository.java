package com.generation.blogpessoal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.generation.blogpessoal.model.Tema;

@Repository // acessa o banco de dados e busca informações // interage com o banco de dados
public interface TemaRepository extends JpaRepository <Tema, Long>{

	 public List <Tema> findAllByDescricaoContainingIgnoreCase (String descricao);


}

