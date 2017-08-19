package br.com.fiap.commons;

import java.time.LocalDate;

public class GifVO {

	private Integer idGif;	
	private String nome;
	private String descricao;
	private LocalDate dataPublicacao;
	private Double classificacao;
	private String caminho;
	
	

	public GifVO(Integer idGif, String nome, String descricao, LocalDate dataPublicacao, Double classificacao,
			String caminho) {
		super();
		this.idGif = idGif;
		this.nome = nome;
		this.descricao = descricao;
		this.dataPublicacao = dataPublicacao;
		this.classificacao = classificacao;
		this.caminho = caminho;
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
	public String getCaminho() {
		return caminho;
	}
	public void setCaminho(String caminho) {
		this.caminho = caminho;
	}
	
}
