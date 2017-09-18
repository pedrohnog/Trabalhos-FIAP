package br.com.fiap.commons;

import java.io.Serializable;
import java.time.LocalDate;

public class GifVO implements Serializable {

	private static final long serialVersionUID = 4412174721871991942L;
	
	private Integer idGif;
	private String nome;
	private String descricao;
	private transient LocalDate dataPublicacao;
	private Double classificacao;
	private String caminhoAnimado;
	private String caminhoEstatico;

	public GifVO(Integer idGif, String nome, String descricao, LocalDate dataPublicacao, Double classificacao,
			String caminhoAnimado, String caminhoEstatico) {
		this.idGif = idGif;
		this.nome = nome;
		this.descricao = descricao;
		this.dataPublicacao = dataPublicacao;
		this.classificacao = classificacao;
		this.caminhoAnimado = caminhoAnimado;
		this.caminhoEstatico = caminhoEstatico;
	}

	public Integer getIdGif() {
		return idGif;
	}

	public void setIdGif(Integer idGif) {
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

	public String getCaminhoAnimado() {
		return caminhoAnimado;
	}

	public void setCaminhoAnimado(String caminhoAnimado) {
		this.caminhoAnimado = caminhoAnimado;
	}

	public String getCaminhoEstatico() {
		return caminhoEstatico;
	}

	public void setCaminhoEstatico(String caminhoEstatico) {
		this.caminhoEstatico = caminhoEstatico;
	}

	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + ((idGif == null) ? 0 : idGif.hashCode());
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
		GifVO other = (GifVO) obj;
		if (idGif == null) {
			if (other.idGif != null)
				return false;
		} else if (!idGif.equals(other.idGif))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return nome;
	}

}
