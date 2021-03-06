package br.com.fiap.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.fiap.dao.DaoGenerico;
import br.com.fiap.entity.Categoria;

public class CategoriaDao extends  DaoGenerico<Categoria>{

	public CategoriaDao() {
		super(Categoria.class);
	}
	
	public boolean cadastrarCategoria(Categoria categoria) {
		if (this.buscarCategoria(categoria.getNome()) == null) {
			super.adicionar(categoria);
			return true;
		}
		return false;
	}
	
	public Categoria buscarCategoria (String nome) {
		StringBuilder sb = new StringBuilder();
		sb.append("select c from Categoria c where nome = '").append(nome).append("'");
		
		TypedQuery<Categoria> query = super.em.createQuery(sb.toString(), Categoria.class);
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public List<Categoria> listarCategorias(){
		TypedQuery<Categoria> query = super.em.createQuery("select c from Categoria c ", Categoria.class);
		try {
			return query.getResultList();
		} catch (NoResultException e) {
			return new ArrayList<>();
		}
	}
	
}
