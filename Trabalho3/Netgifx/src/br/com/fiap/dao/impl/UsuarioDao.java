package br.com.fiap.dao.impl;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.fiap.dao.DaoGenerico;
import br.com.fiap.entity.Usuario;

public class UsuarioDao extends DaoGenerico<Usuario> {

	public UsuarioDao() {
		super(Usuario.class);
	}

	public boolean cadastrarUsuario(Usuario usuario) {
		if (buscarUsuario(usuario.getApelido()) == null) {
			super.adicionar(usuario);
			return true;
		}
		return false;
	}

	public void alterarUsuario(Usuario usuario) {
		super.atualizar(usuario);
	}

	public Usuario buscarUsuario(String apelido) {
		StringBuilder sb = new StringBuilder();
		sb.append("select u from Usuario u where apelido = '").append(apelido).append("'");
		
		TypedQuery<Usuario> query = super.em.createQuery(sb.toString(), Usuario.class);
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
}
