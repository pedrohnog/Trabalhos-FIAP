package br.com.fiap.managedbeans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.fiap.commons.TesteCategoriaMock;

@ManagedBean
@ViewScoped
public class CategoriasBean {

	List<TesteCategoriaMock> lista = null;

	public List<TesteCategoriaMock> getLista() {
		if (lista == null || lista.isEmpty()) {
			lista = new ArrayList<>();
			
			lista.add(new TesteCategoriaMock("01"));
			lista.add(new TesteCategoriaMock("02"));
			lista.add(new TesteCategoriaMock("03"));
			lista.add(new TesteCategoriaMock("04"));
			lista.add(new TesteCategoriaMock("05"));
			lista.add(new TesteCategoriaMock("06"));
			lista.add(new TesteCategoriaMock("07"));
			lista.add(new TesteCategoriaMock("08"));
		}
		return lista;
	}

	public void setLista(List<TesteCategoriaMock> lista) {
		if (lista == null || lista.isEmpty()) {
			lista = new ArrayList<>();
		}
		this.lista = lista;
	}

}
