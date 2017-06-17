package br.com.fiap.bot.validacao;

import br.com.fiap.bot.constantes.EnumComandosBot;

public class IntegracaoBotConsultaTarifaPaga extends IntegracaoBotConsulta {

	@Override
	public String integrarBanco(String resposta, EnumComandosBot comandoBot) {
		return "TARIFA";
	}

	@Override
	public String exibeMensagemPrimeiraIntegracao() {
		return "TARIFA";
	}

}
