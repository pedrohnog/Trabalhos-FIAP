package br.com.fiap.bot.dao.impl;

import br.com.fiap.bot.dao.DaoGenericoBot;
import br.com.fiap.bot.entidades.MensagemIntegracao;

public class MensagemIntegracaoDao extends DaoGenericoBot<MensagemIntegracao> {
	
	public MensagemIntegracaoDao() {
		super(MensagemIntegracao.class);
	}
	
	public void adicionarMensagem(MensagemIntegracao mensagemIntegracao) {
		super.adicionar(mensagemIntegracao);
	}

}
