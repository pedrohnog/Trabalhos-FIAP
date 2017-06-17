package br.com.fiap.bot.validacao;

import br.com.fiap.bot.constantes.EnumComandosBot;

public class IntegracaoBotConsultaSaque extends IntegracaoBotConsulta {

	@Override
	public String integrarBanco(String resposta, EnumComandosBot comandoBot) {
		return "SAQUE";
	}

	@Override
	public String exibeMensagemPrimeiraIntegracao() {
		return "SAQUE";
	}

}
