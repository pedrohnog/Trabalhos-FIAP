package br.com.fiap.bot.validacao;

import br.com.fiap.bot.contantes.EnumComandosBot;

public class IntegracaoBotStart extends IntegracaoBotConsulta {

	@Override
	public String integrarBanco(String resposta, EnumComandosBot comandoBot) {
		return "";
	}

	@Override
	public String exibeMensagemPrimeiraIntegracao() {
		return "Olá ?!\nMeu nome é Pedrite, sou um atendente virtual e irei ajudar você.\nPara saber o que você pode fazer por aqui, digite /ajuda";
	}

}
