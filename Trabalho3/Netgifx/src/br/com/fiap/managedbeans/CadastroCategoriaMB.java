package br.com.fiap.managedbeans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import br.com.fiap.commons.CategoriaVO;


@ManagedBean
@RequestScoped
public class CadastroCategoriaMB {

	private String descricaoCategoria;
	List<CategoriaVO> categoriasCadastradas = new ArrayList<>();
	
   @PostConstruct
    public void init() {		
	   	categoriasCadastradas.add(new CategoriaVO(1, "Filmes", null));
		categoriasCadastradas.add(new CategoriaVO(1, "Filmes", null));
		categoriasCadastradas.add(new CategoriaVO(1, "Filmes", null));
		categoriasCadastradas.add(new CategoriaVO(1, "Filmes", null));
		categoriasCadastradas.add(new CategoriaVO(1, "Filmes", null));
		categoriasCadastradas.add(new CategoriaVO(1, "Filmes", null));
		categoriasCadastradas.add(new CategoriaVO(1, "Filmes", null));
    }

	public String getDescricaoCategoria() {
		return descricaoCategoria;
	}


	public void setDescricaoCategoria(String descricaoCategoria) {
		this.descricaoCategoria = descricaoCategoria;
	}


	public List<CategoriaVO> getCategoriasCadastradas() {
		return categoriasCadastradas;
	}

	public void setCategoriasCadastradas(List<CategoriaVO> categoriasCadastradas) {
		this.categoriasCadastradas = categoriasCadastradas;
	}

	public void cadastrarCategoria(){
		 FacesContext.getCurrentInstance().addMessage(
                 null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                 "Categoria cadastrada com sucesso!", "Categoria cadastrada com sucesso!"));
		 this.descricaoCategoria = null;		 
	}
	
	
}
