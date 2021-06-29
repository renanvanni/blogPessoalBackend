package org.generation.blogpessoalgen.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity // entidade, ou seja, fala para o programa trabalhar com tabelas
@Table(name = "tb_postagem") // cria essa tabela no MySQL
public class Postagem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // identifica id como chave primária
	private long id;

	@NotNull(message = "Campo Obrigatório!")
	@Size(min = 5, max = 100) // determina o tamanho min e max do titulo
	private String title;

	@NotNull(message = "Campo Obrigatório!")
	@Size(min = 5, max = 500)
	private String text;

	@Temporal(TemporalType.TIMESTAMP)
	private Date date = new java.sql.Date(System.currentTimeMillis()); // ao programa rodar, essa linha tráz exatamente a data e hora da postagem
	
	//quando chegar em postagem na classe tema, pare de apresentar a informação
	@ManyToOne
	@JsonIgnoreProperties("postagem")
	private Tema tema;
	
	@ManyToOne
	@JsonIgnoreProperties("postagem")
	private Usuario usuario;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Tema getTema() {
		return tema;
	}

	public void setTema(Tema tema) {
		this.tema = tema;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
