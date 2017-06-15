package br.com.fiap.bot.validacao;

import br.com.fiap.bot.contantes.EnumComandosBot;

public class IntegracaoBotConsultaExtrato extends IntegracaoBotConsulta {

	@Override
	public String integrarBanco(String resposta, EnumComandosBot comandoBot) {
		return "EXTRATO";
	}

	@Override
	public String exibeMensagemPrimeiraIntegracao() {
		return "EXTRATO";
	}

}
