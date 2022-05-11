package com.generation.blogpessoal.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_usuarios")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull(message = "O nome é obrigatório!")
	private String nome;

	@NotNull(message = "O usuário é obrigatório!")
	@Email(message = "O usuário deve ser um email válido!")
	private String usuario;

	@NotBlank(message = "O atributo Senha é Obrigatório!") // @NotBlank indica que o atributo não deve ser nulo e/ou
															// conter espaços em branco.
	@Size(min = 8, message = "A Senha deve ter no mínimo 8 caracteres") // A anotação @Size está definida apenas com o
																		// valor min pq ao criptografar a senha, terá um
																		// tamanho muito maior (em n de caracteres) do
																		// que a senha não ciptografada.
	private String senha;

	private String foto;

	/**
	 * OBS: CascadeType.REMOVE -> Ele propaga a operação de remoção de um objeto Pai
	 * para um objeto Filho. Apenas quando remover a Entidade Usuario, também será
	 * removida todas as entidades Postagens associadas. Nas demais operações não
	 * haverá a propagação. CascadeType.ALL -> Ele propaga todas a operações
	 * (Inserir, Listar, Atualizar e Apagar)de um objeto Pai para um objeto Filho.
	 */
	@OneToMany(mappedBy = "usuario", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties("usuario")
	private List<Postagem> postagem;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public List<Postagem> getPostagem() {
		return postagem;
	}

	public void setPostagem(List<Postagem> postagem) {
		this.postagem = postagem;
	}

}
