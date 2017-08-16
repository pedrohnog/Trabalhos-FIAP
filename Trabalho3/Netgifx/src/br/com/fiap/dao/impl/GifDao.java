package br.com.fiap.dao.impl;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.fiap.dao.DaoGenerico;
import br.com.fiap.entity.Gif;

public class GifDao extends DaoGenerico<Gif>{

	public GifDao() {
		super(Gif.class);
		
	}

	/**
	 * Cria um novo gif
	 * 
	 * @param gif Objeto Gif que será persistido em BD
	 * 
	 * @return <code>true</code> se o gif não existir e conseguir criar um novo, se não <code>false</code>
	 */
	public boolean cadastrarGif(Gif gif) {
		if (this.buscarGif(gif.getNome()) == null) {
			super.adicionar(gif);
			return true;
		}
		return false;
	}
	
	public Gif buscarGif(Integer id) {
		TypedQuery<Gif> query = super.em.createQuery("select g from Gif g where idGif = '" + id + "'", Gif.class);
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public Gif buscarGif(String nome) {
		TypedQuery<Gif> query = super.em.createQuery("select g from Gif g where nome = '" + nome + "'", Gif.class);
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
}
