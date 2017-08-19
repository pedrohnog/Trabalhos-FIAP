package br.com.fiap.commons;

import java.util.List;


public class CategoriaVO {

	private Integer idCategoria;	
	private String nome;
	private List<GifVO> gifs;
	
	public CategoriaVO(Integer idCategoria, String nome,List<GifVO> gifs ){
		this.idCategoria = idCategoria;
		this.nome = nome;
		this.gifs = gifs;
	}
	
	public int getIdCategoria() {
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
	public List<GifVO> getGifs() {
		for(GifVO gif : this.gifs){
			System.out.println("GIF - " + gif.getNome());
		}
		return gifs;
	}
	public void setGifs(List<GifVO> gifs) {
		this.gifs = gifs;
	}	
	
}
