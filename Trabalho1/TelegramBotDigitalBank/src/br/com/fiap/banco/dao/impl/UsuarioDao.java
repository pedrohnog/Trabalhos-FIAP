package br.com.fiap.banco.dao.impl;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.fiap.banco.entidades.Usuario;
import br.com.fiap.generico.dao.DaoGenerico;

public class UsuarioDao extends DaoGenerico<Usuario> {

	public UsuarioDao() {
		super(Usuario.class);
	}

	public void criarUsuario(Usuario usuario) {
		super.adicionar(usuario);
	}

	public void alterarUsuario(Usuario usuario) {
		super.atualizar(usuario);
	}

	public boolean temUsuario(long id) {
		return (super.buscar(id) != null);
	}

	public Usuario buscarUsuario(String cpf) {
		TypedQuery<Usuario> query = super.em.createQuery("select u from Usuario u where cpf = '" + cpf + "'", Usuario.class);
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
}
