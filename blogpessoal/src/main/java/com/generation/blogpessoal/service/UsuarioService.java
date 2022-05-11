package com.generation.blogpessoal.service;

import java.nio.charset.Charset;

import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.generation.blogpessoal.model.Usuario;
import com.generation.blogpessoal.model.UsuarioLogin;
import com.generation.blogpessoal.repository.UsuarioRepository;

/**
 * // Classe UsuarioService implementa as regras de negócio do Recurso Usuario.
 * Regras de negócio são as particularidades das funcionalidades a serem
 * implementadas no objeto tais como:
 * 
 * 1) O Usuário não pode estar duplicado no Banco de dados 2) A senha do Usuario
 * deve ser criptografada
 * 
 * // Toda a implementação dos metodos Cadastrar, Atualizar e Logar estão
 * implmentadas na classe de serviço, enquanto a Classe Controller se limita a
 * checar a resposta da requisição.
 */

@Service // @Service indica que esta é uma Classe de Serviço, ou seja, implementa todas
			// regras de negócio do Recurso Usuário.
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	public Optional<Usuario> cadastrarUsuario(Usuario usuario) {

		if (usuarioRepository.findByUsuario(usuario.getUsuario()).isPresent())
			return Optional.empty(); // isPresent = verifica se o usuário está cadastrado, e se tiver, não cadastra
										// dbv)

		usuario.setSenha(criptografarSenha(usuario.getSenha()));
		/**
		 * Se o Usuário não existir no Banco de Dados, a senha será criptografada
		 * através do Método criptografarSenha.
		 */

		return Optional.of(usuarioRepository.save(usuario));
		/**
		 * Assim como na Expressão Lambda, o resultado do método save será retornado
		 * dentro de um Optional, com o Usuario persistido no Banco de Dados.
		 * 
		 * of​ -> Retorna um Optional com o valor fornecido, mas o valor não pode ser
		 * nulo. Se não tiver certeza de que o valor não é nulo use ofNullable.
		 */
	}

	public Optional<Usuario> atualizarUsuario(Usuario usuario) { // atualiza o usuário

		if (usuarioRepository.findById(usuario.getId()).isPresent()) {
			// Checa se o usuário já existe no Banco de Dados através do método findById, pq
			// não é possível atualizar um usuário inexistente.
			// Se não existir retorna um Optional vazio.
			// isPresent() -> Se um valor estiver presente retorna true, caso contrário
			// retorna false.

			usuario.setSenha(criptografarSenha(usuario.getSenha()));
			// Se o Usuário existir no Banco de Dados, a senha será criptografada através do
			// Método criptografarSenha

			return Optional.ofNullable(usuarioRepository.save(usuario));
			/**
			 * Assim como na Expressão Lambda, o resultado do método save será retornado
			 * dentro de um Optional, com o Usuario persistido no Banco de Dados ou um
			 * Optional vazio, caso aconteça algum erro.
			 * 
			 * ofNullable​ -> Se um valor estiver presente, retorna um Optional com o valor,
			 * caso contrário, retorna um Optional vazio.
			 */
		}

		return Optional.empty(); // empty -> Retorna uma instância de Optional vazia, caso o usuário não seja
									// encontrado

	}

	public Optional<UsuarioLogin> autenticarUsuario(Optional<UsuarioLogin> usuarioLogin) {
//autenticarUsuario gerar o token do usuário codificado em Base64
//O login é executado pela BasicSecurityConfig em conjunto com as classes UserDetailsService e Userdetails

		Optional<Usuario> usuario = usuarioRepository.findByUsuario(usuarioLogin.get().getUsuario());
		/**
		 * // Cria um objeto Optional do tipo Usuario para receber o resultado do método
		 * findByUsuario().
		 * 
		 * Observe que o método autenticarUsuario recebe como parâmetro um objeto da
		 * Classe UsuarioLogin, ao invés de Usuario.
		 * 
		 * get() -> Se um valor estiver presente no objeto ele retorna o valor, caso
		 * contrário, lança uma Exception NoSuchElementException. Então para usar get é
		 * preciso ter certeza de que o Optional não está vazio. O get() funciona como
		 * uma chave que abre o Objeto Optional e permite acessar os Métodos do Objeto
		 * encpsulado.
		 * 
		 */

		if (usuario.isPresent()) { // verifica se o usuario existe

			if (compararSenhas(usuarioLogin.get().getSenha(), usuario.get().getSenha())) {
				// senha //senha criptografada
				// Verifica se a senha enviada, depois de criptografada, é igual a senha gravada
				// no Banco de Dados, através do Método compararSenhas.
				// O Método Retorna verdadeiro se as senhas forem iguais, e falso caso contrário

				usuarioLogin.get().setId(usuario.get().getId());
				usuarioLogin.get().setNome(usuario.get().getNome());
				usuarioLogin.get().setFoto(usuario.get().getFoto());
				usuarioLogin.get()
						.setToken(gerarBasicToken(usuarioLogin.get().getUsuario(), usuarioLogin.get().getSenha()));
				usuarioLogin.get().setSenha(usuario.get().getSenha());
				// Se as senhas forem iguais(a senha e a criptografia, no caso), atualiza o
				// objeto usuarioLogin com os dados
				// recuperados do Banco de Dados e insere o Token Gerado através do Método
				// gerarBasicToken.
				// Desta forma, será possível exibir o nome e a foto do usuário no Frontend.

				return usuarioLogin; // Retorna o objeto usarioLogin atualizado para a classe UsuarioController. A
										// Classe controladora checará se deu tudo certo nesta operação e retornará o
										// status
			}
		}

		return Optional.empty(); // empty -> Retorna uma instância de Optional vazia, caso o usuário não seja
									// encontrado.

	}

	private String criptografarSenha(String senha) { // método que criptografa senhas

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		return encoder.encode(senha); // O método encode retorna a senha criptografada no formato BCrypt
		/**
		 * Instancia um objeto da Classe BCryptPasswordEncoder para criptografar a senha
		 * do usuário.
		 */

	}

	private boolean compararSenhas(String senhaDigitada, String senhaBanco) { // método comparar Senhas.

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		return encoder.matches(senhaDigitada, senhaBanco);
		// vai ver se as senhas deu match -> verifica se a senha enviada, depois de
		// criptografada, é igual a senha gravada no Banco de Dados
		// matches -> Verifca se a senha codificada obtida do banco de dados corresponde
		// à senha enviada depois que ela também for codificada.
		// Retorna verdadeiro se as senhas coincidem e falso se não coincidem.

	}

	private String gerarBasicToken(String usuario, String senha) { // método Gerar Basic Token

		String token = usuario + ":" + senha;
		byte[] tokenBase64 = Base64.encodeBase64(token.getBytes(Charset.forName("US-ASCII")));
		return "Basic " + new String(tokenBase64);
		
		/**
		 * A primeira linha, monta uma String (token) seguindo o padrão Basic, através
		 * da concatenação de caracteres que será codificada (Não criptografada) no
		 * formato Base64, através da Dependência Apache Commons Codec.
		 * 
		 * Essa String tem o formato padrão: <username>:<password> que não pode ser
		 * alterado
		 *
		 * Na segunda linha, faremos a codificação em Base 64 da String.
		 * 
		 * Observe que o vetor tokenBase64 é do tipo Byte para receber o resultado da
		 * codificação, porquê durante o processo é necessário trabalhar diretamente com
		 * os bits (0 e 1) da String
		 * 
		 * Base64.encodeBase64 -> aplica o algoritmo de codificação do Código Decimal
		 * para Base64, que foi gerado no próximo método. Para mais detalhes, veja
		 * Codificação 64 bits na Documentação.
		 * 
		 * Charset.forName("US-ASCII") -> Retorna o codigo ASCII (formato Decimal) de
		 * cada caractere da String. Para mais detalhes, veja a Tabela ASCII na
		 * Documentação.
		 *
		 * Na terceira linha, acrescenta a palavra Basic acompanhada de um espaço em
		 * branco (Obrigatório), além de converter o vetor de Bytes novamente em String
		 * e concatenar tudo em uma única String.
		 * 
		 * O espaço depois da palavra Basic é obrigatório. Caso não seja inserrido, o
		 * Token não será reconhecido.
		 */

	}

}
