package br.com.fiap.dao.impl;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.fiap.dao.DaoGenerico;
import br.com.fiap.entity.Usuario;

/**
 * Classe respons�vel por tratar o DAO do Usuario
 *
 */
public class UsuarioDao extends DaoGenerico<Usuario>{

	public UsuarioDao() {
		super(Usuario.class);
	}

	/**
	 * Cria um novo usu�rio
	 * 
	 * @param usuario Objeto Usuario que ser� persistido em BD
	 * 
	 * @return <code>true</code> se o usu�rio n�o existir e conseguir criar um novo, se n�o <code>false</code>
	 */
	public boolean criarUsuario(Usuario usuario) {
		if (this.buscarUsuario(usuario.getCpf()) == null) {
			super.adicionar(usuario);
			return true;
		}
		return false;
	}

	/**
	 * Altera os dados do usu�rio
	 * 
	 * @param usuario Objeto Usuario que ser� alterado em BD
	 */
	public void alterarUsuario(Usuario usuario) {
		super.atualizar(usuario);
	}

	/**
	 * Busca o usu�rio pelo CPF
	 * 
	 * @param cpf CPF do usu�rio
	 * 
	 * @return Objeto Usuario
	 */
	public Usuario buscarUsuario(String apelido) {
		TypedQuery<Usuario> query = super.em.createQuery("select u from Usuario u where apelido = '" + apelido + "'", Usuario.class);
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
}
