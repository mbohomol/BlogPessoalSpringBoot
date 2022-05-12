package com.generation.blogpessoal.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity // create table // entidade = tabela
@Table(name = "tb_temas") // nome da tabela: tb_temas
public class Tema {

	@Id // primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; // atributo de "Tema"

	@NotBlank(message = "O atributo descrição é obrigatório!")
	@Size(min = 15, max = 200, message = "O atributo descrição deve conter no mínimo 15 e no máximo 200 caracteres ")
	private String descricao; // atributo de "Tema"

	@OneToMany(mappedBy = "tema", cascade = CascadeType.REMOVE) // indica que a Classe Categoria terá um relacionamento
																// do tipo One To Many (Um para Muitos) com a Classe Produto
	// mappedBy = "categoria": indica qual Objeto será utilizado como "chave estrangeira" no relacionamento
	// cascade = CascadeType.REMOVE = exemplo: se apagar uma Categoria, todas os produtos associadas a esta Categoria serão apagados também
	@JsonIgnoreProperties("tema") // @JsonIgnoreProperties evita loop
	private List<Postagem> postagem; // List = listará todos os Produtos associados com as respectivas Categorias

// get and setters:
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Postagem> getPostagem() {
		return postagem;
	}

	public void setPostagem(List<Postagem> postagem) {
		this.postagem = postagem;
	}

}
