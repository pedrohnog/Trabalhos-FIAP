package br.com.fiap.banco.dao.impl;

import br.com.fiap.banco.dao.DaoGenerico;
import br.com.fiap.banco.entidades.Transacao;

public class TransacaoDao extends DaoGenerico<Transacao>{

	public TransacaoDao() {
		super(Transacao.class);
		// TODO Auto-generated constructor stub
	}
	
	public void adicionarTransacao(Transacao transacao) {
		super.adicionar(transacao);
	}

	

}
