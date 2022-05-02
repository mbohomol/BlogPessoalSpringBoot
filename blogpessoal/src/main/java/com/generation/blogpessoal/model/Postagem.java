package com.generation.blogpessoal.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.UpdateTimestamp;

@Entity // create table // entidade = tabela
@Table(name =  "tb_postagens") // nome da tabela: tb_postagens
public class Postagem {

    @Id // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private Long id; // atributo de "Postagens"

    @NotBlank(message = "O atributo título é obrigatório!")
    @Size(min= 5, max= 100, message = "O atributo título deve conter no mínimo 5 e no máximo 100 caracteres ")
    private String titulo; // atributo de "Postagens"

    @NotNull(message = "O atributo título é obrigatório!")
    @Size(min= 10, max= 1000, message = "O atributo título deve conter no mínimo 5 e no máximo 100 caracteres ")
    private String texto; // atributo de "Postagens"

    @UpdateTimestamp
    private LocalDateTime data;  // métodos GET/SET(id/titulo/texto)
    
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
}
