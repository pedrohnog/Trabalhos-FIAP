package br.com.fiap.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "CATEGORIA")
public class Categoria implements Serializable{

	private static final long serialVersionUID = 4612019662284754381L;

	public Categoria(){
	}
	
	public Categoria(String nome){
		this.nome = nome;
	}
	
	public Categoria(int idCategoria, String nome){
		this.idCategoria = idCategoria;
		this.nome = nome;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idCategoria")
	private int idCategoria;

	@Column(name = "nome", length = 45)
	private String nome;

	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "categorias")
	private Set<Gif> gifs;

	public Integer getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Set<Gif> getGifs() {
		return gifs;
	}

	public void setGifs(Set<Gif> gifs) {
		this.gifs = gifs;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idCategoria;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Categoria)) {
			return false;
		}
		Categoria other = (Categoria) obj;
		if (idCategoria != other.idCategoria) {
			return false;
		}
		if (nome == null) {
			if (other.nome != null) {
				return false;
			}
		} else if (!nome.equals(other.nome)) {
			return false;
		}
		return true;
	}

}
