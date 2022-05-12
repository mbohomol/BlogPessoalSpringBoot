package com.generation.blogpessoal.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity // create table // entidade = tabela // gera uma tabela no Banco de Dados
@Table(name = "tb_postagens") //cria o nome da tabela: tb_postagens no Banco de Dados
public class Postagem {

	@Id // @Id indica que o atributo é a chave primária da tabela (primary key)
	@GeneratedValue(strategy = GenerationType.IDENTITY) // @GeneratedValue indica que a chave primária será gerada automaticamente pelo Banco de Dados 
	private Long id; // atributo de "Postagens"        // strategy indica como a Chave Primária será gerada //  GenerationType.IDENTITY = auto_increment

	@NotBlank(message = "O atributo título é obrigatório!")// @NotBlank indica que um atributo não pode ser nulo e não pode ser deixado em branco // a mensagem será exibida se o atributo for nulo ou se for deixado em branco
	@Size(min = 5, max = 100, message = "O atributo título deve conter no mínimo 5 e no máximo 100 caracteres ")  // @Size tem a função de definir o tamanho minimo e máximo de caracteres de um atributo String
	private String titulo; // atributo de "Postagens"

	@NotNull(message = "O atributo título é obrigatório!")
	@Size(min = 10, max = 1000, message = "O atributo título deve conter no mínimo 5 e no máximo 100 caracteres ")
	private String texto; // atributo de "Postagens"

	@UpdateTimestamp // @UpdateTimestamp indica a data e hora do sistema, sempre que a Postagem for
						// atualizada o atributo também será atualizado
	private LocalDateTime data;

	// métodos GET/SET(id/titulo/texto)

	@ManyToOne
	@JsonIgnoreProperties("postagem")// @JsonIgnoreProperties evita loop
	private Tema tema;

	@ManyToOne
	@JsonIgnoreProperties("postagem")// @JsonIgnoreProperties evita loop
	private Usuario usuario;

// get and setters:
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

	public Tema getTema() {
		return tema;
	}

	public void setTema(Tema tema) {
		this.tema = tema;
	}

}
