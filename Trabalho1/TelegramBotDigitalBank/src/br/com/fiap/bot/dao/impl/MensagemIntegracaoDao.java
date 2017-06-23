package br.com.fiap.bot.dao.impl;

import br.com.fiap.bot.entidades.MensagemIntegracao;
import br.com.fiap.generico.dao.DaoGenerico;

/**
 * Classe responsável por tratar o DAO das Mensagens de Integração
 *
 */
public class MensagemIntegracaoDao extends DaoGenerico<MensagemIntegracao> {
	
	public MensagemIntegracaoDao() {
		super(MensagemIntegracao.class);
	}
	
	/**
	 * Grava uma mensagem de integração
	 * 
	 * @param mensagemIntegracao A mensagem recebida pelo Bot
	 */
	public void adicionarMensagem(MensagemIntegracao mensagemIntegracao) {
		super.adicionar(mensagemIntegracao);
	}

}
