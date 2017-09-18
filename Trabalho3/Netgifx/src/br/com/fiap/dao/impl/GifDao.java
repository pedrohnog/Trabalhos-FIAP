package br.com.fiap.dao.impl;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.fiap.dao.DaoGenerico;
import br.com.fiap.entity.Gif;

public class GifDao extends DaoGenerico<Gif>{

	public GifDao() {
		super(Gif.class);
		
	}

	public boolean cadastrarGif(Gif gif) {
		if (this.buscarGif(gif.getNome()) == null) {
			super.adicionar(gif);
			return true;
		}
		return false;
	}
	
	public Gif buscarGif(Integer id) {
		StringBuilder sb = new StringBuilder();
		sb.append("select g from Gif g where idGif = '").append(id).append("'");
		
		TypedQuery<Gif> query = super.em.createQuery(sb.toString(), Gif.class);
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public Gif buscarGif(String nome) {
		StringBuilder sb = new StringBuilder();
		sb.append("select g from Gif g where nome = '").append(nome).append("'");
		
		TypedQuery<Gif> query = super.em.createQuery(sb.toString(), Gif.class);
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
}
