package br.com.fiap.bot.dao.impl;

import br.com.fiap.bot.entidades.MensagemIntegracao;
import br.com.fiap.generico.dao.DaoGenerico;

/**
 * Classe respons�vel por tratar o DAO das Mensagens de Integra��o
 *
 */
public class MensagemIntegracaoDao extends DaoGenerico<MensagemIntegracao> {
	
	public MensagemIntegracaoDao() {
		super(MensagemIntegracao.class);
	}
	
	/**
	 * Grava uma mensagem de integra��o
	 * 
	 * @param mensagemIntegracao A mensagem recebida pelo Bot
	 */
	public void adicionarMensagem(MensagemIntegracao mensagemIntegracao) {
		super.adicionar(mensagemIntegracao);
	}

}
