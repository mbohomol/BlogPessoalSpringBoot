package com.generation.blogpessoal.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.generation.blogpessoal.model.Usuario;

@Repository// Repository assina e implementa o método na Controller e na Classe Serviço
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	/**
	 * Método que busca um usuário pelo seu usuario (email).
	 * 
	 * select * from tb_usuarios where usuario = "usuario procurado"
	 */

	public Optional<Usuario> findByUsuario(String usuario);

	//Optional = pq pode ou não encontrar o usuário/ serve para checar se o usuário existe ou não
	


}
