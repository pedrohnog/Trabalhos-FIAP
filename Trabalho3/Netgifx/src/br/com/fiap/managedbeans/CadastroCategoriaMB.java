package br.com.fiap.managedbeans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import br.com.fiap.commands.NetgifxCommand;
import br.com.fiap.entity.Categoria;


@ManagedBean
@RequestScoped
public class CadastroCategoriaMB {

	private String descricaoCategoria;
	List<Categoria> categoriasCadastradas;
	
   @PostConstruct
    public void init() {		
	   NetgifxCommand command = new NetgifxCommand();
	   categoriasCadastradas = command.listarCategorias();
    }

	public String getDescricaoCategoria() {
		return descricaoCategoria;
	}


	public void setDescricaoCategoria(String descricaoCategoria) {
		this.descricaoCategoria = descricaoCategoria;
	}


	public List<Categoria> getCategoriasCadastradas() {
		return categoriasCadastradas;
	}

	public void setCategoriasCadastradas(List<Categoria> categoriasCadastradas) {
		this.categoriasCadastradas = categoriasCadastradas;
	}
	

	public void cadastrarCategoria(){
		
		NetgifxCommand command = new NetgifxCommand();

		if (command.buscarCategoria(descricaoCategoria) == null) {
			
			command.cadastrarCategoria(new Categoria(descricaoCategoria));
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Categoria cadastrada com sucesso!", "Categoria cadastrada com sucesso!"));

			this.descricaoCategoria = null;

		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Categoria já cadastrada!", "Categoria já cadastrada!"));
		}
		
			 
	}
	
	
}
