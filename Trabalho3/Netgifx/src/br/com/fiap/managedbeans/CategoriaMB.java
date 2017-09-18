package br.com.fiap.managedbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.fiap.commands.NetgifxCommand;
import br.com.fiap.entity.Categoria;
import br.com.fiap.entity.Gif;

@ManagedBean
@ViewScoped
public class CategoriaMB implements Serializable {

	private static final long serialVersionUID = 3779695316443811228L;
	
	private List<Categoria> categorias = new ArrayList<>();
	private Gif gifSelecionado;

	private transient NetgifxCommand netgifxCommand = new NetgifxCommand();

	@PostConstruct
	public void init() {
		categorias = netgifxCommand.listarCategorias();

		if (categorias == null) {
			categorias = new ArrayList<>();
		}
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public Gif getGifSelecionado() {
		return gifSelecionado;
	}

	public void setGifSelecionado(Gif gifSelecionado) {
		this.gifSelecionado = gifSelecionado;
	}

}