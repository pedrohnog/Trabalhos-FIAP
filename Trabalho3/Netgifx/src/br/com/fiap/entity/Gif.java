package br.com.fiap.entity;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "GIF")
public class Gif {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idGif")
	private int idGif;
	
	@Column(name = "Nome", length = 45)
	private String nome;
	
	@Column(name = "Descricao", length = 45)
	private String descricao;
	
	@Column(name = "DataPublicacao")
	private LocalDate dataPublicacao;
	
	@Column(name = "Classificacao")
	private Double classificacao;
	
	@Column(name = "Caminho", length = 45)
	private String caminho;

	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "gifs")
	private Set<Usuario> usuarios;
	
	@ManyToMany(fetch = FetchType.EAGER,cascade=CascadeType.MERGE)
	@JoinTable(name="GIF_CATEGORIA", 
			joinColumns=@JoinColumn(name="idGif"),
			inverseJoinColumns=@JoinColumn(name="idCategoria"))
	private Set<Categoria> categorias;
	
	public int getIdGif() {
		return idGif;
	}

	public void setIdGif(int idGif) {
		this.idGif = idGif;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public LocalDate getDataPublicacao() {
		return dataPublicacao;
	}

	public void setDataPublicacao(LocalDate dataPublicacao) {
		this.dataPublicacao = dataPublicacao;
	}

	public Double getClassificacao() {
		return classificacao;
	}

	public void setClassificacao(Double classificacao) {
		this.classificacao = classificacao;
	}

	public String getCaminho() {
		return caminho;
	}

	public void setCaminho(String caminho) {
		this.caminho = caminho;
	}

	public Set<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Set<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Set<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(Set<Categoria> categorias) {
		this.categorias = categorias;
	}
	
}
