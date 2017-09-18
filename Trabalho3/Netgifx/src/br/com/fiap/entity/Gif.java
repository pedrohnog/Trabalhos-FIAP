package br.com.fiap.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
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
public class Gif implements Serializable {

	private static final long serialVersionUID = 480069580562973503L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idGif")
	private int idGif;

	@Column(name = "nome", length = 45)
	private String nome;

	@Column(name = "descricao", length = 200)
	private String descricao;

	@Column(name = "dataPublicacao")
	private transient LocalDate dataPublicacao;

	@Column(name = "caminho", length = 45)
	private String caminho;

	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "gifs")
	private Set<Usuario> usuarios;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinTable(name = "GIF_CATEGORIA", joinColumns = @JoinColumn(name = "idGif"), inverseJoinColumns = @JoinColumn(name = "idCategoria"))
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
		if(categorias == null){
			categorias = new HashSet<>();
		}
		return categorias;
	}

	public void setCategorias(Set<Categoria> categorias) {
		this.categorias = categorias;
	}

	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + idGif;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Gif other = (Gif) obj;
		return idGif == other.idGif;
	}
	
}
