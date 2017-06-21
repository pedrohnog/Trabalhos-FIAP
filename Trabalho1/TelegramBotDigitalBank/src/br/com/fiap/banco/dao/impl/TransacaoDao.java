package br.com.fiap.banco.dao.impl;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.fiap.banco.constantes.TipoTransacao;
import br.com.fiap.banco.entidades.Transacao;
import br.com.fiap.generico.dao.DaoGenerico;

/**
 * Classe responsável por tratar o DAO da Transacao
 *
 */
public class TransacaoDao extends DaoGenerico<Transacao>{

	public TransacaoDao() {
		super(Transacao.class);
	}
	
	/**
	 * Adiciona as informações de uma nova transação
	 * 
	 * @param transacao Objeto Transacao que será persistido em BD
	 */
	public void adicionarTransacao(Transacao transacao) {
		super.adicionar(transacao);
	}

	/**
	 * Listar as transações já efetivadas na conta
	 * 
	 * @param conta Id do Telegram
	 * @param tipoTransacao Tipo de transação que deve ser retornada
	 * 
	 * @return Lista das transações
	 */
	public List<Transacao> buscarTransacoes(long conta, TipoTransacao tipoTransacao) {
		//TODO Revisar para incluir empréstimo
		TypedQuery<Transacao> query = super.em.createQuery("select t from Transacao t where conta = " + conta + " and tipo_transacao = " + tipoTransacao.getCodigo(), Transacao.class);
		try {
			return query.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}

}
