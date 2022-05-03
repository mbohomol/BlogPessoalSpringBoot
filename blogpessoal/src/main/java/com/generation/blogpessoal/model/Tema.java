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
@Table(name =  "tb_temas") // nome da tabela: tb_temas
public class Tema {
	
		@Id // primary key
	    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
	    private Long id; // atributo de "Tema"

	    @NotBlank(message = "O atributo descrição é obrigatório!")
	    @Size(min= 5, max= 100, message = "O atributo descrição deve conter no mínimo 5 e no máximo 100 caracteres ")
	    private String descricao; // atributo de "Tema"
	    
	    @OneToMany(mappedBy = "tema", cascade = CascadeType.REMOVE)
	    @JsonIgnoreProperties("tema")
	    private List<Postagem> postagem;

	    
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
