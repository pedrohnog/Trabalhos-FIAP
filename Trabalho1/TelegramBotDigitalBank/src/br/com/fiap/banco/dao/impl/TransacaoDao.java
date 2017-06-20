package br.com.fiap.banco.dao.impl;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.fiap.banco.constantes.TipoTransacao;
import br.com.fiap.banco.entidades.Transacao;
import br.com.fiap.generico.dao.DaoGenerico;

public class TransacaoDao extends DaoGenerico<Transacao>{

	public TransacaoDao() {
		super(Transacao.class);
	}
	
	public void adicionarTransacao(Transacao transacao) {
		super.adicionar(transacao);
	}

	public List<Transacao> buscarTransacoes(long conta, TipoTransacao tipoTransacao) {
		TypedQuery<Transacao> query = super.em.createQuery("select t from Transacao t where conta = " + conta + " and tipo_transacao = " + tipoTransacao.getCodigo(), Transacao.class);
		try {
			return query.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}

}
