package br.com.fiap.managedbeans;
 
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.fiap.commons.CategoriaVO;
import br.com.fiap.commons.GifVO;
 
@ManagedBean
@ViewScoped
public class CategoriaMB implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private List<CategoriaVO> categorias;
	private GifVO gifSelecionado;
         
    @PostConstruct
    public void init() {
    	if(categorias == null){
    		categorias = new ArrayList<>();
    	}
    	System.out.println("Criando categorias!");
    	categorias.addAll(CategoriaService.INSTANCIA.criarCategorias("Lancamentos"));
    	categorias.addAll(CategoriaService.INSTANCIA.criarCategorias("Infantil"));
    	categorias.addAll(CategoriaService.INSTANCIA.criarCategorias("Familia"));
    	categorias.addAll(CategoriaService.INSTANCIA.criarCategorias("Escola"));
    	categorias.addAll(CategoriaService.INSTANCIA.criarCategorias("Faculdade"));
    	categorias.addAll(CategoriaService.INSTANCIA.criarCategorias("Creche"));
    	categorias.addAll(CategoriaService.INSTANCIA.criarCategorias("Animais"));
    }

	public List<CategoriaVO> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<CategoriaVO> categorias) {
		this.categorias = categorias;
	}

	public GifVO getGifSelecionado() {
		System.out.println("Retornando GIF selecionado");
		return gifSelecionado;
	}

	public void setGifSelecionado(GifVO gifSelecionado) {
		System.out.println("Atualizando GIF selecionado");
		this.gifSelecionado = gifSelecionado;
	}
    
}