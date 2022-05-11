package com.generation.blogpessoal.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.generation.blogpessoal.model.Usuario;
import com.generation.blogpessoal.repository.UsuarioRepository;

//A Classe UserDetailsServiceImpl implementa a interface UserDetailsService
// A interface UserDetailsService é responsável por recuperar os dados do usuário no Banco de Dados pelo usuário 
// e converter em um objeto da Classe UserDetailsImpl.
//Por se tratar de uma implementação de uma interface, a classe deve ter em seu nome o sufixo Impl para indicar que se trata de uma implementação.


@Service // = @Service indica que esta é uma Classe de Serviço, ou seja, implementa
			// regras de negócio da aplicação
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UsuarioRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		// A implementação de autenticação chama o método loadUserByUsername(String
		// username), para obter os dados de um usuário com um determinado nome de
		// usuário.
		// O nome do usuário deve ser único. O usuário retornado por este método é um
		// objeto da classe UserDetailsImpl.

		Optional<Usuario> usuario = userRepository.findByUsuario(userName); // findByUsuario = serve para buscar o usuário no Banco de dados. que foi
																			// assinado na interface UsuarioRepository

		usuario.orElseThrow(() -> new UsernameNotFoundException(userName + " not found.")); // Se o usuário não existir, o método lança uma 
																							// Exception do tipo UsernameNotFoundException

		return usuario.map(UserDetailsImpl::new).get();
		/**
		 * Retorna um objeto do tipo UserDetailsImpl criado com os dados recuperados do
		 * Banco de dados.
		 * 
		 * O operador :: faz parte de uma expressão que referencia um método,
		 * complementando uma função lambda. Neste exemplo, o operador faz referência ao
		 * construtor da Classe UserDetailsImpl.
		 */
	}
}
