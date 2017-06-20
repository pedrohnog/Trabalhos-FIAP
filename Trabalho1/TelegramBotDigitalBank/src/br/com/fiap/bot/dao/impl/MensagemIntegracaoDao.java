package br.com.fiap.bot.dao.impl;

import br.com.fiap.bot.entidades.MensagemIntegracao;
import br.com.fiap.generico.dao.DaoGenerico;

public class MensagemIntegracaoDao extends DaoGenerico<MensagemIntegracao> {
	
	public MensagemIntegracaoDao() {
		super(MensagemIntegracao.class);
	}
	
	public void adicionarMensagem(MensagemIntegracao mensagemIntegracao) {
		super.adicionar(mensagemIntegracao);
	}

}
